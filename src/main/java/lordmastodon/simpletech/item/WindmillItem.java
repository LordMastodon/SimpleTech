package lordmastodon.simpletech.item;

import lordmastodon.simpletech.SimpleTech;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WindmillItem extends Item {

	 public boolean onItemUse(ItemStack is, EntityPlayer ep, World w, int x, int y, int z, int side, float x2, float y2, float z2) {
		 if(!w.isRemote) {
			 if(side == 1 && w.getBlock(x, y, z).equals(SimpleTech.WindmillGround) && w.getBlockMetadata(x, y, z) == 5) {
				 boolean notEnoughSpace = false;
				 
				 for(int x1 = -1; x1 < 1; x1++) {
					 for(int z1 = -1; z1 < 1; z1++) {
						 for(int y1 = 0; y1 < 7; y1++) {
							 if(!w.isAirBlock(x+x1, y+y1+1, z+z1)) notEnoughSpace = true;
						 }
					 }
				 }
				 
				 if(!notEnoughSpace) {
					 int direction = (-(int)ep.rotationYaw+45)/90;
					 
					 if(direction == 0) direction = 4;
					 
					 for(int y1 = 0; y1 < 7; y1++) {
						 w.setBlock(x, y+y1+1, z, SimpleTech.Windmill, (y1+1) == 7 ? (y1+1+direction):(y1+1), 2);
					 }
					 
					 return true;
				
				 }
					 
			 }
			 			 
		 }
		 
		 return false;
	 }
	
}