package lordmastodon.simpletech.tileentity;

import lordmastodon.simpletech.SimpleTech;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileEntityRenderWindmill extends TileEntitySpecialRenderer{

	private final ResourceLocation texture = new ResourceLocation(SimpleTech.MODID, "textures/model/Windmill.png");
	private final ResourceLocation textureTop = new ResourceLocation(SimpleTech.MODID, "textures/blocks/WindmillTop.png");
	private final ResourceLocation textureRestOfWindmill = new ResourceLocation(SimpleTech.MODID, "textures/model/RestOfWindmill.png");
	
	private float pixel = 1F/16F;	
	
	private int tW = 32;
	private int tH = 32;
	
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
		int x1 = te.xCoord;
		int y1 = te.yCoord;
		int z1 = te.zCoord;
		
		while(te.getWorldObj().getBlockMetadata(x1, y1, z1) < 7 && te.getWorldObj().getBlock(x1, y1, z1).equals(SimpleTech.Windmill)) {
			y1++;
		}
		
		int metadata = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);
		int direction = te.getWorldObj().getBlockMetadata(x1, y1, z1)-7;
				
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glTranslatef(0.5F, 0, 0.5F);
		GL11.glRotatef(direction*90, 0, 1, 0);
		GL11.glTranslatef(-0.5F, 0, -0.5F);
		Tessellator t = Tessellator.instance;
		this.bindTexture(texture);
		t.startDrawingQuads();
		{
			if(metadata > 0 && metadata < 7) {
				t.addVertexWithUV((16-8)/2*pixel, 0, 1-(16-8)/2*pixel, 8*1F/tW, 1*1F/tH);
				t.addVertexWithUV((16-8)/2*pixel, 1, 1-(16-8)/2*pixel, 8*1F/tW, 0*1F/tH);
				t.addVertexWithUV((16-8)/2*pixel, 1, (16-8)/2*pixel, 0*1F/tW, 0*1F/tH);
				t.addVertexWithUV((16-8)/2*pixel, 0, (16-8)/2*pixel, 0*1F/tW, 1*1F/tH);
				
				t.addVertexWithUV(1-(16-8)/2*pixel, 0, 1-(16-8)/2*pixel, 8*1F/tW, 1*1F/tH);
				t.addVertexWithUV(1-(16-8)/2*pixel, 1, 1-(16-8)/2*pixel, 8*1F/tW, 0*1F/tH);
				t.addVertexWithUV((16-8)/2*pixel, 1, 1-(16-8)/2*pixel, 0*1F/tW, 0*1F/tH);
				t.addVertexWithUV((16-8)/2*pixel, 0, 1-(16-8)/2*pixel, 0*1F/tW, 1*1F/tH);
				
				t.addVertexWithUV((16-8)/2*pixel, 0, (16-8)/2*pixel, 8*1F/tW, 1*1F/tH);
				t.addVertexWithUV((16-8)/2*pixel, 1, (16-8)/2*pixel, 8*1F/tW, 0*1F/tH);
				t.addVertexWithUV(1-(16-8)/2*pixel, 1, (16-8)/2*pixel, 0*1F/tW, 0*1F/tH);
				t.addVertexWithUV(1-(16-8)/2*pixel, 0, (16-8)/2*pixel, 0*1F/tW, 1*1F/tH);
				
				t.addVertexWithUV(1-(16-8)/2*pixel, 0, (16-8)/2*pixel, 8*1F/tW, 1*1F/tH);
				t.addVertexWithUV(1-(16-8)/2*pixel, 1, (16-8)/2*pixel, 8*1F/tW, 0*1F/tH);
				t.addVertexWithUV(1-(16-8)/2*pixel, 1, 1-(16-8)/2*pixel, 0*1F/tW, 0*1F/tH);
				t.addVertexWithUV(1-(16-8)/2*pixel, 0, 1-(16-8)/2*pixel, 0*1F/tW, 1*1F/tH);
				
			}
			
			if(metadata > 7) {
				this.bindTexture(textureTop);
				t.addVertexWithUV(1, 1, 1, 1, 1);
				t.addVertexWithUV(1, 1, 0, 1, 0);
				t.addVertexWithUV(0, 1, 0, 0, 0);
				t.addVertexWithUV(0, 1, 1, 0, 1);
				
				t.addVertexWithUV(0, 0, 1, 0, 0);
				t.addVertexWithUV(0, 0, 0, 0, 1);
				t.addVertexWithUV(1, 0, 0, 1, 1);
				t.addVertexWithUV(1, 0, 1, 1, 0);
				
				t.addVertexWithUV(1, 0, 1, 1, 1);
				t.addVertexWithUV(1, 1, 1, 1, 0);
				t.addVertexWithUV(0, 1, 1, 0, 0);
				t.addVertexWithUV(0, 0, 1, 0, 1);

				
				t.addVertexWithUV(1, 0, 0, 1, 0);
				t.addVertexWithUV(0, 0, 0, 0, 0);
				t.addVertexWithUV(0, 1, 0, 0, 1);
				t.addVertexWithUV(1, 1, 0, 1, 1);
				
				t.addVertexWithUV(1, 0, 0, 1, 1);
				t.addVertexWithUV(1, 1, 0, 1, 0);
				t.addVertexWithUV(1, 1, 1, 0, 0);
				t.addVertexWithUV(1, 0, 1, 0, 1);
				
				t.addVertexWithUV(0, 0, 0, 1, 1);
				t.addVertexWithUV(0, 0, 1, 0, 1);
				t.addVertexWithUV(0, 1, 1, 0, 0);
				t.addVertexWithUV(0, 1, 0, 1, 0);
				
			}
		}
		t.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);
		if(metadata > 7) {
			drawRotor(te);
		} 
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
		
	}
	
	private void drawRotor(TileEntity te) {
		TileEntityWindmill te1 = (TileEntityWindmill) te.getWorldObj().getTileEntity(te.xCoord, te.yCoord, te.zCoord);
		
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslatef(0, 0.5F, 0.5F);
		GL11.glRotatef(te1.rotation*2, 1, 0, 0);
		GL11.glTranslatef(0, -0.5F, -0.5F);
		
		GL11.glDisable(GL11.GL_CULL_FACE);
		Tessellator t = Tessellator.instance;
		this.bindTexture(texture);
		t.startDrawingQuads();
		{
			t.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 1*pixel+0.5F, 9*(1F/tW), 1*(1F/tH));
			t.addVertexWithUV(-2*pixel, 3.5F, 1*pixel+0.5F, 9*(1F/tW), 0*(1F/tH));
			t.addVertexWithUV(-2*pixel, 3.5F, -1*pixel+0.5F, 8*(1F/tW), 0*(1F/tH));
			t.addVertexWithUV(-2*pixel, 0.5F+1*pixel, -1*pixel+0.5F, 8*(1F/tW), 1*(1F/tH));
			
			t.addVertexWithUV(-2*pixel, -2.5F, 1*pixel+0.5F, 9*(1F/tW), 1*(1F/tH));
			t.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 1*pixel+0.5F, 9*(1F/tW), 0*(1F/tH));
			t.addVertexWithUV(-2*pixel, 0.5F-1*pixel, -1*pixel+0.5F, 8*(1F/tW), 0*(1F/tH));
			t.addVertexWithUV(-2*pixel, -2.5F, -1*pixel+0.5F, 8*(1F/tW), 1*(1F/tH));

			t.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 3.5F, 9*(1F/tW), 1*(1F/tH));
			t.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 3.5F, 9*(1F/tW), 0*(1F/tH));
			t.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 0.5F+1*pixel, 8*(1F/tW), 0*(1F/tH));
			t.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 0.5F+1*pixel, 8*(1F/tW), 1*(1F/tH));

			t.addVertexWithUV(-2*pixel, 0.5F-1*pixel, 0.5F-1*pixel, 9*(1F/tW), 1*(1F/tH));
			t.addVertexWithUV(-2*pixel, 0.5F+1*pixel, 0.5F-1*pixel, 9*(1F/tW), 0*(1F/tH));
			t.addVertexWithUV(-2*pixel, 0.5F+1*pixel, -2.5F, 8*(1F/tW), 0*(1F/tH));
			t.addVertexWithUV(-2*pixel, 0.5F-1*pixel, -2.5F, 8*(1F/tW), 1*(1F/tH));
		}
		t.draw();
		this.bindTexture(textureRestOfWindmill);
		t.startDrawingQuads();
		{
			t.addVertexWithUV(-2*pixel, 7*pixel, 9*pixel, 6*(1F/10F), 6*(1F/10F));	
			t.addVertexWithUV(-2*pixel, 9*pixel, 9*pixel, 6*(1F/10F), 4*(1F/10F));	
			t.addVertexWithUV(-2*pixel, 9*pixel, 7*pixel, 4*(1F/10F), 4*(1F/10F));	
			t.addVertexWithUV(-2*pixel, 7*pixel, 7*pixel, 4*(1F/10F), 6*(1F/10F));	
	
			t.addVertexWithUV(-2*pixel, 7*pixel, 7*pixel, 6*(1F/10F), 6*(1F/10F));	
			t.addVertexWithUV(-2*pixel, 9*pixel, 7*pixel, 6*(1F/10F), 4*(1F/10F));	
			t.addVertexWithUV(0*pixel, 9*pixel, 7*pixel, 4*(1F/10F), 4*(1F/10F));	
			t.addVertexWithUV(0*pixel, 7*pixel, 7*pixel, 4*(1F/10F), 6*(1F/10F));	
	
			t.addVertexWithUV(0*pixel, 7*pixel, 9*pixel, 6*(1F/10F), 4*(1F/10F));	
			t.addVertexWithUV(0*pixel, 9*pixel, 9*pixel, 6*(1F/10F), 6*(1F/10F));	
			t.addVertexWithUV(-2*pixel, 9*pixel, 9*pixel, 4*(1F/10F), 6*(1F/10F));	
			t.addVertexWithUV(-2*pixel, 7*pixel, 9*pixel, 4*(1F/10F), 4*(1F/10F));	
	
			t.addVertexWithUV(0*pixel, 9*pixel, 9*pixel, 6*(1F/10F), 6*(1F/10F));	
			t.addVertexWithUV(0*pixel, 9*pixel, 7*pixel, 6*(1F/10F), 4*(1F/10F));	
			t.addVertexWithUV(-2*pixel, 9*pixel, 7*pixel, 4*(1F/10F), 4*(1F/10F));	
			t.addVertexWithUV(-2*pixel, 9*pixel, 9*pixel, 4*(1F/10F), 6*(1F/10F));	
			
			t.addVertexWithUV(-2*pixel, 7*pixel, 9*pixel, 4*(1F/10F), 4*(1F/10F));	
			t.addVertexWithUV(-2*pixel, 7*pixel, 7*pixel, 4*(1F/10F), 6*(1F/10F));	
			t.addVertexWithUV(0*pixel, 7*pixel, 7*pixel, 6*(1F/10F), 6*(1F/10F));	
			t.addVertexWithUV(0*pixel, 7*pixel, 9*pixel, 6*(1F/10F), 4*(1F/10F));	
		}
		t.draw();
		t.startDrawingQuads();
		{
			
		}
		t.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		GL11.glEnable(GL11.GL_LIGHTING);
	}

}
