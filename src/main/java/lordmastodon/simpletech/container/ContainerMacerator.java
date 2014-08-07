package lordmastodon.simpletech.container;

import lordmastodon.simpletech.item.MaceratedPowder;
import lordmastodon.simpletech.tileentity.TileEntityMacerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerMacerator
        extends Container
{
    private TileEntityMacerator macerator;
    public int lastBurnTime;
    public int lastCookTime;

    public ContainerMacerator(InventoryPlayer inventory, TileEntityMacerator tileEntity)
    {
        this.macerator = tileEntity;
        addSlotToContainer(new Slot(tileEntity, 0, 56, 24));
        addSlotToContainer(new Slot(tileEntity, 1, 116, 42));
        addSlotToContainer(new SlotFurnace(inventory.player, tileEntity, 3, 129, 34));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    public void addingCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.macerator.cookTime);
        icrafting.sendProgressBarUpdate(this, 1, this.macerator.power);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.macerator.cookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.macerator.cookTime);
            }

            if (this.lastBurnTime != this.macerator.power)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.macerator.power);
            }
        }

        this.lastCookTime = this.macerator.cookTime;
        this.lastBurnTime = this.macerator.power;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue)
    {
        if (slot == 0) {
            this.macerator.cookTime = newValue;
        }
        if (slot == 1) {
            this.macerator.power = newValue;
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer ep, int j)
    {
        ItemStack is = null;
        Slot slot = (Slot)this.inventorySlots.get(j);

        if (slot != null && slot.getHasStack())
        {
            ItemStack is1 = slot.getStack();
            is = is1.copy();

            if (j == 2)
            {
                if (!this.mergeItemStack(is1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(is1, is);
            }
            else if (j != 1 && j != 0)
            {
                if (MaceratedPowder.macerating().getMaceratingResult(is) != null)
                {
                    if (!this.mergeItemStack(is1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (j >= 3 && j < 30)
                {
                    if (!this.mergeItemStack(is1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (j >= 30 && j < 39 && !this.mergeItemStack(is1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(is1, 3, 39, false))
            {
                return null;
            }

            if (is1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (is1.stackSize == is.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(ep, is1);
        }

        return is;
    }

    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.macerator.isUseableByPlayer(entityPlayer);
    }
}
