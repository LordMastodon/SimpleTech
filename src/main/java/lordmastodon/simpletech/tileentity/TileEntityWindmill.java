package lordmastodon.simpletech.tileentity;

import lordmastodon.simpletech.SimpleTech;
import lordmastodon.simpletech.block.Windmill;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWindmill extends TileEntity {

	public static Windmill wi;
	
	public float rotation = 0;
	
	public int maxPower = 1000;
	public float power = 0;
	
	public float powerPerRotation = 0.1F;
	
	public void updateEntity() {
		if(this.getWorldObj().getBlockMetadata(xCoord, yCoord, zCoord) > 6) {
			rotation++;
		} 
	power+=powerPerRotation;
	if(power > maxPower) power = maxPower;		
	}
	
}
