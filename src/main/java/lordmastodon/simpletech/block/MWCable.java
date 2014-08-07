package lordmastodon.simpletech.block;

import lordmastodon.simpletech.tileentity.TileEntityMWCable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class MWCable extends BlockContainer {

	public MWCable() {
		super(Material.ground);
		
		float pixel = 1F/16F;
		this.setBlockBounds(11*pixel/2, 11*pixel/2, 11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2);
				
		this.useNeighborBrightness = true;
	}

	public TileEntity createNewTileEntity(World w, int i) {
		return new TileEntityMWCable();
	}
	
	public int getRenderType() {
		return -2;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}

}
