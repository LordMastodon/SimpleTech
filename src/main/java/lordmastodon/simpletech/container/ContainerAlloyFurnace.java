package lordmastodon.simpletech.container;

import lordmastodon.simpletech.tileentity.TileEntityAlloyFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerAlloyFurnace
  extends Container
{
  private TileEntityAlloyFurnace alloyFurnace;
  public int lastBurnTime;
  public int lastItemBurnTime;
  public int lastCookTime;
  
  public ContainerAlloyFurnace(InventoryPlayer inventory, TileEntityAlloyFurnace tileEntity)
  {
    this.alloyFurnace = tileEntity;
    addSlotToContainer(new Slot(tileEntity, 0, 15, 17));
    addSlotToContainer(new Slot(tileEntity, 1, 51, 17));
    addSlotToContainer(new Slot(tileEntity, 2, 33, 53));
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
  
  public void addingCraftToCrafters(ICrafting icrafting)
  {
    super.addCraftingToCrafters(icrafting);
    icrafting.sendProgressBarUpdate(this, 0, this.alloyFurnace.cookTime);
    icrafting.sendProgressBarUpdate(this, 1, this.alloyFurnace.burnTime);
    icrafting.sendProgressBarUpdate(this, 2, this.alloyFurnace.currentItemBurnTime);
  }
  
  public void detectAndSendChanges()
  {
    super.detectAndSendChanges();
    for (int i = 0; i < this.crafters.size(); i++)
    {
      ICrafting icrafting = (ICrafting)this.crafters.get(i);
      if (this.lastCookTime != this.alloyFurnace.cookTime) {
        icrafting.sendProgressBarUpdate(this, 0, this.alloyFurnace.cookTime);
      }
      if (this.lastBurnTime != this.alloyFurnace.burnTime) {
        icrafting.sendProgressBarUpdate(this, 1, this.alloyFurnace.burnTime);
      }
      if (this.lastItemBurnTime != this.alloyFurnace.currentItemBurnTime) {
        icrafting.sendProgressBarUpdate(this, 2, this.alloyFurnace.currentItemBurnTime);
      }
    }
    this.lastCookTime = this.alloyFurnace.cookTime;
    this.lastBurnTime = this.alloyFurnace.burnTime;
    this.lastItemBurnTime = this.alloyFurnace.currentItemBurnTime;
  }
  
  @SideOnly(Side.CLIENT)
  public void updateProgressBar(int slot, int newValue)
  {
    if (slot == 0) {
      this.alloyFurnace.cookTime = newValue;
    }
    if (slot == 1) {
      this.alloyFurnace.burnTime = newValue;
    }
    if (slot == 2) {
      this.alloyFurnace.currentItemBurnTime = newValue;
    }
  }
  
  public ItemStack transferStackInSlot(EntityPlayer player, int clickedSlotNumber)
  {
    ItemStack itemStack = null;
    Slot slot = (Slot)this.inventorySlots.get(clickedSlotNumber);
    if ((slot != null) && (slot.getHasStack()))
    {
      ItemStack itemStack1 = slot.getStack();
      itemStack = itemStack1.copy();
      if (clickedSlotNumber == 3)
      {
        if (!mergeItemStack(itemStack1, 4, 40, true)) {
          return null;
        }
        slot.onSlotChange(itemStack1, itemStack);
      }
      else if ((clickedSlotNumber != 0) && (clickedSlotNumber != 1) && (clickedSlotNumber != 2))
      {
        if (FurnaceRecipes.smelting().getSmeltingResult(itemStack1) != null)
        {
          if (!mergeItemStack(itemStack1, 0, 2, false)) {
            return null;
          }
        }
        else if (TileEntityAlloyFurnace.isItemFuel(itemStack1))
        {
          if (!mergeItemStack(itemStack1, 2, 3, false)) {
            return null;
          }
        }
        else if ((clickedSlotNumber >= 4) && (clickedSlotNumber < 30))
        {
          if (!mergeItemStack(itemStack1, 30, 40, false)) {
            return null;
          }
        }
        else if ((clickedSlotNumber >= 30) && (clickedSlotNumber < 39) && 
          (!mergeItemStack(itemStack1, 4, 30, false))) {
          return null;
        }
      }
      else if (!mergeItemStack(itemStack1, 4, 40, false))
      {
        return null;
      }
      if (itemStack1.stackSize == 0) {
        slot.putStack((ItemStack)null);
      } else {
        slot.onSlotChanged();
      }
      if (itemStack1.stackSize == itemStack.stackSize) {
        return null;
      }
      slot.onPickupFromSlot(player, itemStack1);
    }
    return itemStack;
  }
  
  public boolean canInteractWith(EntityPlayer entityPlayer)
  {
    return this.alloyFurnace.isUseableByPlayer(entityPlayer);
  }
}
