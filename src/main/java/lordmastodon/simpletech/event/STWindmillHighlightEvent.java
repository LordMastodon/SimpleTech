package lordmastodon.simpletech.event;

import lordmastodon.simpletech.SimpleTech;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class STWindmillHighlightEvent {

	@SubscribeEvent()
	public void onDrawHighlight(DrawBlockHighlightEvent e) {
		if(e.target.typeOfHit.equals(MovingObjectType.BLOCK)) {
			if(e.player.worldObj.getBlock(e.target.blockX, e.target.blockY, e.target.blockZ).equals(SimpleTech.Windmill) && e.player.worldObj.getBlockMetadata(e.target.blockX, e.target.blockY, e.target.blockZ) <= 7) {
				onDrawHighlightWindmill(e);
			}
			
			if(e.player.worldObj.getBlock(e.target.blockX, e.target.blockY, e.target.blockZ).equals(SimpleTech.WindmillGround)) {
				onDrawHighlightWindmillGround(e);
			}
		}
	}
	
	public void onDrawHighlightWindmill(DrawBlockHighlightEvent e) {
		e.setCanceled(true);
		
		Block b = e.player.worldObj.getBlock(e.target.blockX, e.target.blockY, e.target.blockZ);
		b.setBlockBoundsBasedOnState(e.player.worldObj, e.target.blockX, e.target.blockY, e.target.blockZ);
		
		double x = e.player.lastTickPosX + (e.player.posX - e.player.lastTickPosX) * e.partialTicks;
		double y = e.player.lastTickPosY + (e.player.posY - e.player.lastTickPosY) * e.partialTicks;
		double z = e.player.lastTickPosZ + (e.player.posZ - e.player.lastTickPosZ) * e.partialTicks;
		
		float f = 0.002F;
		
		AxisAlignedBB aabb = b.getSelectedBoundingBoxFromPool(e.player.worldObj, e.target.blockX, e.target.blockY, e.target.blockZ).expand(f, f, f).getOffsetBoundingBox(-x, -y, -z);
		
		int metadata = e.player.worldObj.getBlockMetadata(e.target.blockX, e.target.blockY, e.target.blockZ);
		
		Tessellator t = Tessellator.instance;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_BLEND);	
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glDepthMask(false);
		GL11.glLineWidth(2F);
		GL11.glColor4f(0, 0, 0, 0.4F);
		t.startDrawing(GL11.GL_LINES);
		{
			t.addVertex(aabb.minX, aabb.minY+(7 - metadata), aabb.minZ);
			t.addVertex(aabb.minX, aabb.maxY-metadata, aabb.minZ);
			t.addVertex(aabb.maxX, aabb.minY+(7 - metadata), aabb.minZ);
			t.addVertex(aabb.maxX, aabb.maxY-metadata, aabb.minZ);
			t.addVertex(aabb.minX, aabb.minY+(7 - metadata), aabb.maxZ);
			t.addVertex(aabb.minX, aabb.maxY-metadata, aabb.maxZ);
			t.addVertex(aabb.maxX, aabb.minY+(7 - metadata), aabb.maxZ);
			t.addVertex(aabb.maxX, aabb.maxY-metadata, aabb.maxZ);
		}
		t.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_BLEND);	

	}
	
	public void onDrawHighlightWindmillGround(DrawBlockHighlightEvent e) {
		e.setCanceled(true);
		
		double x = e.player.lastTickPosX + (e.player.posX - e.player.lastTickPosX) * e.partialTicks;
		double y = e.player.lastTickPosY + (e.player.posY - e.player.lastTickPosY) * e.partialTicks;
		double z = e.player.lastTickPosZ + (e.player.posZ - e.player.lastTickPosZ) * e.partialTicks;
		
		Tessellator t = Tessellator.instance;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		t.startDrawing(GL11.GL_LINES);
		{
			
		}
		t.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);


	}
	
}
	
