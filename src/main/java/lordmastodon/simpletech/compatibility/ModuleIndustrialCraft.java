package lordmastodon.simpletech.compatibility;

import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.tile.IEnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.api.CompatibilityModule;
import universalelectricity.api.CompatibilityType;

/** @author Calclavia */
public class ModuleIndustrialCraft extends CompatibilityModule
{
    @Override
    public long doReceiveEnergy(Object handler, ForgeDirection direction, long energy, boolean doReceive)
    {
        if (handler instanceof IEnergySink)
        {
            if (doIsEnergyContainer(handler))
            {
                if (doGetEnergy(handler, direction) >= doGetMaxEnergy(handler, direction))
                {
                    return 0;
                }
            }
            int demand = (int) Math.min(((IEnergySink) handler).getMaxSafeInput(), ((IEnergySink) handler).demandedEnergyUnits());
            long send = (long) Math.min(demand, energy * CompatibilityType.INDUSTRIALCRAFT.ratio);

            if (doReceive)
            {
                double rejected = ((IEnergySink) handler).injectEnergyUnits(direction, send);
                return (long) (Math.max(send - rejected, 0) * CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio);
            }

            return (long) (send * CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio);
        }

        return 0;
    }

    @Override
    public long doExtractEnergy(Object handler, ForgeDirection direction, long energy, boolean doExtract)
    {
        if (handler instanceof IEnergySource)
        {
            long demand = (long) Math.min(((IEnergySource) handler).getOfferedEnergy(), energy * CompatibilityType.INDUSTRIALCRAFT.ratio);

            if (doExtract)
            {
                ((IEnergySource) handler).drawEnergy(demand);
            }

            return (long) (demand * CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio);
        }

        return 0;
    }

    @Override
    public boolean doIsHandler(Object obj)
    {
        return obj instanceof IEnergySink || obj instanceof IEnergySource || obj instanceof IElectricItem;
    }

    @Override
    public boolean doCanConnect(Object obj, ForgeDirection direction, Object source)
    {
        // TODO: Does not support multipart sources. Might cause errors.
        try
        {
            if (!(source instanceof TileEntity))
            {
                source = null;
            }

            if (obj instanceof IEnergySink)
            {
                if (((IEnergySink) obj).acceptsEnergyFrom((TileEntity) source, direction))
                    return true;
            }

            if (obj instanceof IEnergySource)
            {
                if (((IEnergySource) obj).emitsEnergyTo((TileEntity) source, direction))
                    return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public long doChargeItem(ItemStack itemStack, long joules, boolean doCharge)
    {
        if (itemStack.getItem() instanceof IElectricItem)
        {
            return (long) (ElectricItem.manager.charge(itemStack, (int) Math.ceil(joules * CompatibilityType.INDUSTRIALCRAFT.ratio), 4, true, !doCharge) * CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio);
        }
        return 0;
    }

    @Override
    public long doDischargeItem(ItemStack itemStack, long joules, boolean doDischarge)
    {
        if (itemStack.getItem() instanceof IElectricItem)
        {
            IElectricItem item = (IElectricItem) itemStack.getItem();

            if (item.canProvideEnergy(itemStack))
            {
                return (long) (ElectricItem.manager.discharge(itemStack, (int) Math.ceil(joules * CompatibilityType.INDUSTRIALCRAFT.ratio), 4, true, !doDischarge) * CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio);
            }
        }
        return 0;
    }

    @Override
    public ItemStack doGetItemWithCharge(ItemStack itemStack, long energy)
    {
        ItemStack is = itemStack.copy();

        ElectricItem.manager.discharge(is, Integer.MAX_VALUE, 1, true, false);
        ElectricItem.manager.charge(is, (int) (energy * CompatibilityType.INDUSTRIALCRAFT.ratio), 1, true, false);

        return is;
    }

    @Override
    public boolean doIsEnergyContainer(Object obj)
    {
        return obj instanceof IEnergyStorage;
    }

    @Override
    public long doGetEnergy(Object obj, ForgeDirection direction)
    {
        return (long) (((IEnergyStorage) obj).getStored() * CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio);
    }

    @Override
    public long doGetMaxEnergy(Object obj, ForgeDirection direction)
    {
        return (long) (((IEnergyStorage) obj).getCapacity() * CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio);
    }

    @Override
    public long doGetEnergyItem(ItemStack is)
    {
        return (long) (ElectricItem.manager.getCharge(is) * CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio);
    }

    @Override
    public long doGetMaxEnergyItem(ItemStack is)
    {
        return (long) (((IElectricItem) is.getItem()).getMaxCharge(is) * CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio);
    }
}