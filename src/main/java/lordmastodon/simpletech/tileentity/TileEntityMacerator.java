package lordmastodon.simpletech.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lordmastodon.simpletech.block.Macerator;
import lordmastodon.simpletech.item.MaceratedPowder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityMacerator extends TileEntity implements ISidedInventory {

    private String localizedName;
    public static int[] slots_top = {0};
    public static int[] slots_bottom = {1};
    public static int[] slots_side = {1};
    public static ItemStack[] slots = new ItemStack[2];
    public static int furnaceSpeed = 150;
    public int power;
    public int maxPower = 4;

    //public int FurnaceSpeed () {
        //if (powerReceived == 1) {
            //return 350;
        //} else if (powerReceived == 2) {
            //return 275;
        //} /else if (powerReceived == 3) {
            //return 200;
        //} else if (powerReceived == 4) {
            //return 100;
        //} else {
            //return 150;
        //}
    //}

    public int cookTime;

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

        this.power = nbt.getShort("power");
        this.cookTime = nbt.getShort("CookTime");

        if (nbt.hasKey("CustomName")) {
            this.localizedName = nbt.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setShort("power", (short)this.power);
        nbt.setShort("CookTime", (short)this.cookTime);

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

    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.localizedName : "container.macerator";
    }

    public boolean isInvNameLocalized()
    {
        return this.localizedName != null && this.localizedName.length() > 0;
    }

    public int getSizeInventory()
    {
        return this.slots.length;
    }

    public ItemStack getStackInSlot(int var1)
    {
        return this.slots[var1];
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
        return hasCustomInventoryName() ? this.localizedName : "container.macerayot";
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

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1)
    {
        return this.cookTime * par1 / furnaceSpeed;
    }

    public int getPowerRemainingScaled(int par1){
        return this.power * par1 / this.maxPower;
    }

    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this;
    }

    public boolean hasPower()
    {
        return this.power > 0;
    }

    public boolean isMacerating(){
        return this.cookTime > 0;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public void updateEntity(){
        boolean flag = this.power > 0;
        boolean flag1 = false;

        if (hasPower() && isMacerating()){
            this.power--;
        }

        if (!this.worldObj.isRemote){
            if (this.power < this.maxPower){

                flag1 = true;

                if (this.slots[1] != null){
                    this.slots[1].stackSize--;

                    if (this.slots[1].stackSize == 0){
                        this.slots[1] = this.slots[1].getItem().getContainerItem(slots[1]);
                    }
                }
            }

            if (this.hasPower() && this.canSmelt())
            {
                ++this.cookTime;

                if (this.cookTime == furnaceSpeed)
                {
                    this.cookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                this.cookTime = 0;
            }

            if (flag != this.power > 0)
            {
                flag1 = true;
                Macerator.updateMaceratorBlockState(this.power > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1){
            this.markDirty();
        }
    }

    public boolean isOre(ItemStack itemstack){
        String[] oreNames = OreDictionary.getOreNames();

        for(int i = 0; i < oreNames.length; i++){
            if(oreNames[i].contains("ore")){
                if(OreDictionary.getOres(oreNames[i]) != null){
                    if(OreDictionary.getOres(oreNames[i]).get(0).getItem() == itemstack.getItem()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean canSmelt()
    {
        if (this.slots[0] == null){
            return false;
        }else{
            ItemStack itemstack = MaceratedPowder.macerating().getMaceratingResult(this.slots[0]);
            if(itemstack == null) return false;
            if(!isOre(this.slots[0])) return false;
            if(this.slots[1] == null) return true;
            if(!this.slots[1].isItemEqual(itemstack)) return false;
            int result = slots[1].stackSize + (itemstack.stackSize*2);
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = MaceratedPowder.macerating().getMaceratingResult(this.slots[0]);

            if (this.slots[1] == null) {
                this.slots[1] = itemstack.copy();
                this.slots[1].stackSize *= 2;
            } else if (this.slots[1].isItemEqual(itemstack)) {
                slots[1].stackSize += (itemstack.stackSize * 2);
            }

            --this.slots[0].stackSize;

            if (this.slots[0].stackSize <= 0) {
                this.slots[0] = null;
            }
        }
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
}
