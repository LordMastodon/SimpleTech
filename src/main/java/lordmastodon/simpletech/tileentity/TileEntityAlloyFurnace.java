package lordmastodon.simpletech.tileentity;

import lordmastodon.simpletech.block.AlloyFurnace;
import lordmastodon.simpletech.handler.AlloyFurnaceRecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityAlloyFurnace extends TileEntity implements ISidedInventory {
	
//  ItemStack alloyOutput = AlloyFurnaceRecipeHandler.getSmeltingResult(this.slots[0].getItem(), this.slots[1].getItem());
  private String localizedName;
  private static final int[] slots_top = { 0, 1 };
  private static final int[] slots_bottom = { 2, 3 };
  private static final int[] slots_side = { 2 };
  private ItemStack[] slots = new ItemStack[4];
  public static int furnaceSpeed = 150;
  public int burnTime;
  public int currentItemBurnTime;
  public int cookTime;
  public int powerRecieved;
  
  public void readFromNBT(NBTTagCompound nbt)
  {
    super.readFromNBT(nbt);
    
    NBTTagList list = nbt.getTagList("Items", 10);
    this.slots = new ItemStack[getSizeInventory()];
    for (int i = 0; i < list.tagCount(); i++)
    {
      NBTTagCompound compound = list.getCompoundTagAt(i);
      byte b = compound.getByte("Slot");
      if ((b >= 0) && (b < this.slots.length)) {
        this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
      }
    }
    this.burnTime = nbt.getShort("BurnTime");
    this.cookTime = nbt.getShort("CookTime");
    this.currentItemBurnTime = nbt.getShort("CurrentBurnTime");
    if (nbt.hasKey("CustomName")) {
      this.localizedName = nbt.getString("CustomName");
    }
  }
  
  public void writeToNBT(NBTTagCompound nbt)
  {
    super.writeToNBT(nbt);
    
    nbt.setShort("BurnTime", (short)this.burnTime);
    nbt.setShort("CookTime", (short)this.cookTime);
    nbt.setShort("CurrentItemBurnTime", (short)this.currentItemBurnTime);
    
    NBTTagList list = new NBTTagList();
    for (int i = 0; i < this.slots.length; i++) {
      if (this.slots[i] != null)
      {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setByte("Slot", (byte)i);
        this.slots[i].writeToNBT(compound);
        list.appendTag(compound);
      }
    }
    nbt.setTag("Items", list);
    if (hasCustomInventoryName()) {
      nbt.setString("CustomName", this.localizedName);
    }
  }
  
  public int getSizeInventory()
  {
    return this.slots.length;
  }
  
  public ItemStack getStackInSlot(int i)
  {
    return this.slots[i];
  }
  
  public ItemStack decrStackSize(int i, int j) {
	  if(this.slots[i] != null){
			ItemStack itemstack;
				
			if(this.slots[i].stackSize <= j){
				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;
			} else {
				itemstack = this.slots[i].splitStack(j);
				
				if(this.slots[i].stackSize == 0){
					this.slots[i] = null;
				}
				
				return itemstack;
			}
	  } else {
		  ItemStack itemstack = null;
		  return itemstack;
	  }
  }
  
  public ItemStack getStackInSlotOnClosing(int i)
  {
	  if(this.slots[i] != null){
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
	  } else {
		  ItemStack itemstack = this.slots[i];
		  return itemstack;
	  }
  }
  
  public void setInventorySlotContents(int i, ItemStack itemStack)
  {
    this.slots[i] = itemStack;
    if ((itemStack != null) && (itemStack.stackSize > getInventoryStackLimit())) {
      itemStack.stackSize = getInventoryStackLimit();
    }
  }
  
  public String getInventoryName()
  {
    return hasCustomInventoryName() ? this.localizedName : "container.AlloyFurnace";
  }
  
  public void setGuiDisplayName(String displayName)
  {
    this.localizedName = displayName;
  }
  
  public boolean hasCustomInventoryName()
  {
    return (this.localizedName != null) && (this.localizedName.length() > 0);
  }
  
  public int getInventoryStackLimit()
  {
    return 64;
  }
  
  public boolean isUseableByPlayer(EntityPlayer entityPlayer)
  {
    return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this;
  }
  
  public void openInventory() {}
  
  public void closeInventory() {}
  
  public boolean isBurning()
  {
    return this.burnTime > 0;
  }
  
  public void updateEntity()
  {
    boolean flag = this.burnTime > 0;
    boolean flag1 = false;
    if (isBurning()) {
      this.burnTime -= 1;
    }
    if (!this.worldObj.isRemote)
    {
      if ((this.burnTime == 0) && (this.canSmelt()))
      {
        this.currentItemBurnTime = (this.burnTime = getItemBurnTime(this.slots[2]));
        if (isBurning())
        {
          flag1 = true;
          if (this.slots[2] != null)
          {
            this.slots[2].stackSize -= 1;
            if (this.slots[2].stackSize == 0) {
              this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
            }
          }
        }
      }
      if ((isBurning()) && (canSmelt()))
      {
        this.cookTime += 1;
        if (this.cookTime == furnaceSpeed)
        {
          this.cookTime = 0;
          smeltItem();
          flag1 = true;
        }
      }
      else
      {
        this.cookTime = 0;
      }
      if (flag != isBurning())
      {
        flag1 = true;
        AlloyFurnace.updateAlloyFurnaceBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
      }
    }
    if (flag1) {
      markDirty();
    }
  }
    
  private boolean canSmelt()
  {
//    int itemstackSize = AlloyFurnaceRecipeHandler.getSmeltingResultStackSize();
//    if (alloyOutput == null) {
//      return false;
//    }
//    
//    if (this.slots[3] == null) {
//    	return true;
//	}
//
//    if (!this.slots[3].isItemEqual(alloyOutput)) {
//    	return false;
//	}
//
//    if ((this.slots[3].stackSize + itemstackSize < getInventoryStackLimit() + 1) && (this.slots[3].stackSize + itemstackSize < this.slots[3].getMaxStackSize() + 1)) {
//    	return true;
//	}
//    
//    return this.slots[3].stackSize + itemstackSize < alloyOutput.getMaxStackSize();
	  return true;
  }
  
  public void smeltItem()
  {
//    if (!canSmelt()) {
//      return;
//    }
//    int itemstackSize = AlloyFurnaceRecipeHandler.getSmeltingResultStackSize();
//    if (this.slots[3] == null) {
//      this.slots[3] = alloyOutput.copy();
//    } else if (this.slots[3].getItem() == alloyOutput.getItem()) {
//      this.slots[3].stackSize += itemstackSize;
//    }
//    for (int i = 0; i < 2; i++)
//    {
//      if (this.slots[i].stackSize <= 0) {
//       this.slots[i] = new ItemStack(this.slots[i].getItem().setFull3D());
//      } else {
//        this.slots[i].stackSize -= 1;
//      }
//      if (this.slots[i].stackSize <= 0) {
//       this.slots[i] = null;
//      }
//    }
  }
  
  public static int getItemBurnTime(ItemStack itemStack)
  {
    if (itemStack == null) {
      return 0;
    }
    Item item = itemStack.getItem();
    if (((item instanceof ItemBlock)) && (Block.getBlockFromItem(item) != Blocks.air))
    {
      Block block = Block.getBlockFromItem(item);
      if (block == Blocks.wooden_slab) {
        return 150 * furnaceSpeed / 150;
      }
      if (block.getMaterial() == Material.wood) {
        return 300 * furnaceSpeed / 150;
      }
      if (block == Blocks.coal_block) {
        return 16000 * furnaceSpeed / 150;
      }
      if (block == Blocks.sapling) {
        return 100 * furnaceSpeed / 150;
      }
    }
    if (((item instanceof ItemTool)) && (((ItemTool)item).getToolMaterialName().equals("WOOD"))) {
      return 200 * furnaceSpeed / 150;
    }
    if (((item instanceof ItemSword)) && (((ItemSword)item).getToolMaterialName().equals("WOOD"))) {
      return 200 * furnaceSpeed / 150;
    }
    if (((item instanceof ItemHoe)) && (((ItemHoe)item).getToolMaterialName().equals("WOOD"))) {
      return 200 * furnaceSpeed / 150;
    }
    if (item == Items.stick) {
      return 100 * furnaceSpeed / 150;
    }
    if (item == Items.coal) {
      return 1600 * furnaceSpeed / 150;
    }
    if (item == Items.lava_bucket) {
      return 20000 * furnaceSpeed / 150;
    }
    if (item == Items.blaze_rod) {
      return 2400 * furnaceSpeed / 150;
    }
    return GameRegistry.getFuelValue(itemStack);
  }
  
  public static boolean isItemFuel(ItemStack itemStack)
  {
    return getItemBurnTime(itemStack) > 0;
  }
  
  public boolean isItemValidForSlot(int i, ItemStack itemStack)
  {
    return i != 2;
  }
  
  public int[] getAccessibleSlotsFromSide(int var1)
  {
    return var1 == 1 ? slots_top : var1 == 0 ? slots_bottom : slots_side;
  }
  
  public boolean canInsertItem(int i, ItemStack itemStack, int j)
  {
    return isItemValidForSlot(i, itemStack);
  }
  
  public boolean canExtractItem(int i, ItemStack itemStack, int j)
  {
    return (j != 0) || (i != 1) || (itemStack.getItem() == Items.bucket);
  }
  
  public int getBurnTimeRemainingScaled(int i)
  {
    if (this.currentItemBurnTime == 0) {
      this.currentItemBurnTime = furnaceSpeed;
    }
    return this.burnTime * i / this.currentItemBurnTime;
  }
  
  public int getCookProgressScaled(int i)
  {
    return this.cookTime * i / furnaceSpeed;
  }
}
