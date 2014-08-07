package lordmastodon.simpletech.handler;

import lordmastodon.simpletech.SimpleTech;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AlloyFurnaceRecipeHandler {
	
	static Item firstIngredient;
	static Item secondIngredient;
	
  public static ItemStack getSmeltingResult(Item fI, Item sI) {
	  
	  firstIngredient = fI;
	  secondIngredient = sI;
	  
    return getOutput(fI, sI);
  }
  
  public static int getSmeltingResultStackSize() {
    if (getOutput(firstIngredient, secondIngredient) != null) {
      return getOutput(firstIngredient, secondIngredient).stackSize;
    } else {
    	return 0;
    }
  }
  
  private static ItemStack getOutput(Item fI, Item s1) {
    if (isCorrectItem(Items.apple, Items.arrow)) {
    	return new ItemStack(Items.baked_potato, 1);
    } else {
    	return new ItemStack(SimpleTech.failedAlloy);
    }
  }
  
  public static boolean isCorrectItem(Item fI, Item sI) {
	  if(firstIngredient == fI && secondIngredient == sI) {
		  return true;
	  } else {
		  return false;
	  }
  }
}
