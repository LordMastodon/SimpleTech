package lordmastodon.simpletech.block;

import lordmastodon.simpletech.SimpleTech;
import lordmastodon.simpletech.tileentity.TileEntityWindmill;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Windmill extends BlockContainer {

	public Windmill(Material p_i45394_1_) {
		super(p_i45394_1_);
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess iba, int x, int y, int z) {
		float pixel = 1F/16F;
		
		if(iba.getBlockMetadata(x, y, z) < 7) {
			this.setBlockBounds(pixel*4, 0, pixel*4, 1-pixel*4, 1, 1-pixel*4);
		} else {
			this.setBlockBounds(0, 0, 0, 1, 1, 1);
		}
	}
	
	public int getRenderType() {
		return -1;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public TileEntity createNewTileEntity(World w, int i) {
		return new TileEntityWindmill();
	}
	
	public void breakBlock(World w, int x, int y, int z, Block b, int metadata) {
		if(w.getBlock(x, y+1, z).equals(SimpleTech.Windmill)) {
			w.setBlockToAir(x, y+1, z);
		}
		if(w.getBlock(x, y-1, z).equals(SimpleTech.Windmill)) {
			w.setBlockToAir(x, y-1, z);
		}
	}

}
