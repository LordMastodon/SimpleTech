package lordmastodon.simpletech.block;

import java.util.Random;

import lordmastodon.simpletech.SimpleTech;
import lordmastodon.simpletech.tileentity.TileEntityMacerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Macerator extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;
    @SideOnly(Side.CLIENT)
    private IIcon iconBack;
    @SideOnly(Side.CLIENT)
    private IIcon iconRightSide;
    @SideOnly(Side.CLIENT)
    private IIcon iconLeftSide;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;
    @SideOnly(Side.CLIENT)

    private Random rand = new Random();

    private final boolean isActive;
    private static boolean keepInventory;

    public Macerator(boolean isActive)
    {
        super(Material.rock);

        this.isActive = isActive;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        iconTop = icon.registerIcon(SimpleTech.MODID + ":MaceratorTop");
        iconBottom = icon.registerIcon(SimpleTech.MODID + ":MaceratorBottom");
        iconRightSide = icon.registerIcon(SimpleTech.MODID + ":MaceratorRightSide");
        iconLeftSide = icon.registerIcon(SimpleTech.MODID + ":MaceratorLeftSide");
        iconBack = icon.registerIcon(SimpleTech.MODID + ":MaceratorBack");
        iconFront = icon.registerIcon(SimpleTech.MODID + ":MaceratorFront");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if(side == 0) {
            return iconBottom;
        } else if (side == 1) {
            return iconTop;
        } else if (side == 2) {
            return iconFront;
        } else if (side == 3) {
            return iconBack;
        } else if (side == 5){
            return iconRightSide;
        } else {
        	return iconLeftSide;
        }
    }

    public Item getItemDropped(int par1, Random random, int par3)
    {
        return Item.getItemFromBlock(SimpleTech.MaceratorIdle);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block block1 = world.getBlock(x, y, z - 1);
            Block block2 = world.getBlock(x, y, z + 1);
            Block block3 = world.getBlock(x - 1, y, z);
            Block block4 = world.getBlock(x + 1, y, z);
            byte b0 = 3;
            if ((block1.func_149730_j()) && (!block2.func_149730_j())) {
                b0 = 3;
            }
            if ((block2.func_149730_j()) && (!block1.func_149730_j())) {
                b0 = 2;
            }
            if ((block3.func_149730_j()) && (!block4.func_149730_j())) {
                b0 = 5;
            }
            if ((block4.func_149730_j()) && (!block3.func_149730_j())) {
                b0 = 4;
            }
            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityMacerator();
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (this.isActive)
        {
            int direction = world.getBlockMetadata(x, y, z);

            float x1 = x + 0.5F;
            float y1 = y + random.nextFloat();
            float z1 = z + 0.5F;

            float f = 0.52F;
            float f1 = random.nextFloat() * 0.6F - 0.3F;
            if (direction == 4)
            {
                world.spawnParticle("smoke", x1 - f, y1, z1 + f1, 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", x1 - f, y1, z1 + f1, 0.0D, 0.0D, 0.0D);
            }
            else if (direction == 5)
            {
                world.spawnParticle("smoke", x1 + f, y1, z1 + f1, 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", x1 + f, y1, z1 + f1, 0.0D, 0.0D, 0.0D);
            }
            else if (direction == 2)
            {
                world.spawnParticle("smoke", x1 + f1, y1, z1 - f, 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", x1 + f1, y1, z1 - f, 0.0D, 0.0D, 0.0D);
            }
            else if (direction == 3)
            {
                world.spawnParticle("smoke", x1 + f1, y1, z1 + f, 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", x1 + f1, y1, z1 + f, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        int l = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        if (itemStack.hasDisplayName()) {
            ((TileEntityMacerator)world.getTileEntity(x, y, z)).setGuiDisplayName(itemStack.getDisplayName());
        }
    }

    public static void updateMaceratorBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord)
    {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileEntity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;
        if (active) {
            worldObj.setBlock(xCoord, yCoord, zCoord, SimpleTech.MaceratorActive);
        } else {
            worldObj.setBlock(xCoord, yCoord, zCoord, SimpleTech.MaceratorIdle);
        }
        keepInventory = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
        if (tileEntity != null)
        {
            tileEntity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileEntity);
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata)
    {
        if (!keepInventory)
        {
            TileEntityMacerator tileEntity = (TileEntityMacerator)world.getTileEntity(x, y, z);
            if (tileEntity != null)
            {
                for (int i = 0; i < tileEntity.getSizeInventory(); i++)
                {
                    ItemStack itemStack = tileEntity.getStackInSlot(i);
                    if (itemStack != null)
                    {
                        float f = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
                        while (itemStack.stackSize > 0)
                        {
                            int j = this.rand.nextInt(21) + 10;
                            if (j > itemStack.stackSize) {
                                j = itemStack.stackSize;
                            }
                            itemStack.stackSize -= j;

                            EntityItem item = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));
                            if (itemStack.hasTagCompound()) {
                                item.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
                            }
                            float f3 = 0.05F;
                            item.motionX = ((float)this.rand.nextGaussian() * f3);
                            item.motionY = ((float)this.rand.nextGaussian() * f3 + 0.2F);
                            item.motionZ = ((float)this.rand.nextGaussian() * f3);

                            world.spawnEntityInWorld(item);
                        }
                    }
                }
                world.func_147453_f(x, y, z, oldBlock);
            }
        }
        super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
    }

    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int i)
    {
        return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
    }

    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(SimpleTech.MaceratorIdle);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
      if (!world.isRemote) {
        FMLNetworkHandler.openGui(player, SimpleTech.instance, 1, world, x, y, z);
      }
      return true;
    }
}
