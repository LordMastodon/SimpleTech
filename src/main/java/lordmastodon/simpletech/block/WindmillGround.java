package lordmastodon.simpletech.block;

import lordmastodon.simpletech.SimpleTech;
import lordmastodon.simpletech.tileentity.TileEntityWindmillFloor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WindmillGround extends BlockContainer {

	TileEntityWindmillFloor tewg;
	
	public WindmillGround(Material p_i45394_1_) {
		super(p_i45394_1_);

		this.setBlockBounds(0, 0, 0, 1, 1F/16F*16, 1);
	}		
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool (World w, int x, int y, int z) {
		if(w.getBlockMetadata(x, y, z) == 1) setBlockBounds(0, 0, 0, 0.5F, (1F/16F)*16, 0.5F);
		if(w.getBlockMetadata(x, y, z) == 2) setBlockBounds(0, 0, 0, 0.5F, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 3) setBlockBounds(0, 0, 0.5F, 0.5F, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 4) setBlockBounds(0, 0, 0, 1, (1F/16F)*16, 0.5F);
		if(w.getBlockMetadata(x, y, z) == 5) setBlockBounds(0, 0, 0, 1, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 6) setBlockBounds(0, 0, 0.5F, 1, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 7) setBlockBounds(0.5F, 0, 0, 1, (1F/16F)*16, 0.5F);
		if(w.getBlockMetadata(x, y, z) == 8) setBlockBounds(0.5F, 0, 0, 1, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 9) setBlockBounds(0.5F, 0, 0.5F, 1, (1F/16F)*16, 1);		
		
		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
	}
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z) {
		if(w.getBlockMetadata(x, y, z) == 1) setBlockBounds(0, 0, 0, 0.5F, (1F/16F)*16, 0.5F);
		if(w.getBlockMetadata(x, y, z) == 2) setBlockBounds(0, 0, 0, 0.5F, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 3) setBlockBounds(0, 0, 0.5F, 0.5F, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 4) setBlockBounds(0, 0, 0, 1, (1F/16F)*16, 0.5F);
		if(w.getBlockMetadata(x, y, z) == 5) setBlockBounds(0, 0, 0, 1, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 6) setBlockBounds(0, 0, 0.5F, 1, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 7) setBlockBounds(0.5F, 0, 0, 1, (1F/16F)*16, 0.5F);
		if(w.getBlockMetadata(x, y, z) == 8) setBlockBounds(0.5F, 0, 0, 1, (1F/16F)*16, 1);
		if(w.getBlockMetadata(x, y, z) == 9) setBlockBounds(0.5F, 0, 0.5F, 1, (1F/16F)*16, 1);	
		
		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block nB) {
		updateMultiBlockStructure(w, x, y, z);
	}
	
	public void onBlockAdded(World w, int x, int y, int z) {
		updateMultiBlockStructure(w, x, y, z);
	}
	
	public void updateMultiBlockStructure(World w, int x, int y, int z) {
		isMultiBlockStructure(w, x, y, z);
	}
	
	public int getRenderType() {
		return -1;
	}
	
	public boolean isMultiBlockStructure(World w, int x1, int y1, int z1) {
		boolean mStructure = false;
		boolean currentCheckStructure = true;
		
		for(int x2 = 0; x2 < 3; x2++) {
			for(int z2 = 0; z2 < 3; z2++) {
				if(!mStructure) {
					currentCheckStructure = true;
					for(int x3 = 0; x3 < 3; x3++) {
						for(int z3 = 0; z3 < 3; z3++) {
							if(currentCheckStructure && !w.getBlock(x1+x2-x3, y1, z1+z2-z3).equals(SimpleTech.WindmillGround)) {
								currentCheckStructure = false;
							}
						}	
					}
					
					if(currentCheckStructure) {
						for(int x3 = 0; x3 < 3; x3++) {
							for(int z3 = 0; z3 < 3; z3++) {
								w.setBlockMetadataWithNotify(x1+x2-x3, y1, z1+z2-z3, x3*3+z3+1, 2);
							}	
						}
					}
					
				}
				
				mStructure = currentCheckStructure;
				
			}
		}
		
		if(mStructure) return true;
		
		if(w.getBlockMetadata(x1, y1, z1) > 0) w.setBlockMetadataWithNotify(x1, y1, z1, 0, 3);
		 
		return false;
	}

	public TileEntity createNewTileEntity(World w, int arg1) {
		return new TileEntityWindmillFloor();
	}

}
