package lordmastodon.simpletech.tileentity;

import lordmastodon.simpletech.SimpleTech;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TileEntityRenderWindmillGround extends TileEntitySpecialRenderer {

	private final ResourceLocation texture = new ResourceLocation(SimpleTech.MODID, "textures/model/WindmillFloor.png");
	private final ResourceLocation textureRestOfWindmill = new ResourceLocation(SimpleTech.MODID, "textures/model/RestOfWindmill.png");

	
	private int tW = 32;
	private int tH = 32;
	
	private float pixel = 1F/16F;
	
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		Tessellator t = Tessellator.instance;
		this.bindTexture(texture);
		t.startDrawingQuads();
		{
			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 0) {
				t.addVertexWithUV(1, pixel*16, 1, 1F/tW*(32), 1F/tH*(32));
				t.addVertexWithUV(1, pixel*16, 0, 1F/tW*(32), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*16, 0, 1F/tW*(0), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*16, 1, 1F/tW*(0), 1F/tH*32);
				
				t.addVertexWithUV(1, pixel*0, 1, 1F/tW*(32), 1F/tH*(32));
				t.addVertexWithUV(1, pixel*0, 0, 1F/tW*(32), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*0, 0, 1F/tW*(0), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*0, 1, 1F/tW*(0), 1F/tH*32);
				
				t.addVertexWithUV(1, 0, 1, 1F/tW*(8+16), 1F/tH*(8+16));
				t.addVertexWithUV(1, pixel*16, 1, 1F/tW*(8+16), 1F/tH*(8));
				t.addVertexWithUV(0, pixel*16, 1, 1F/tW*(8), 1F/tH*(8));
				t.addVertexWithUV(0, 0, 1, 1F/tW*(8), 1F/tH*(8+16));
				
				t.addVertexWithUV(1, 0, 0, 1F/tW*(8+16), 1F/tH*(8));
				t.addVertexWithUV(0, 0, 0, 1F/tW*(8), 1F/tH*(8));
				t.addVertexWithUV(0, pixel*16, 0, 1F/tW*(8), 1F/tH*(8+16));
				t.addVertexWithUV(1, pixel*16, 0, 1F/tW*(8+16), 1F/tH*(8+16));
			
				t.addVertexWithUV(1, 0, 0, 1F/tW*(8+16), 1F/tH*(8+16));
				t.addVertexWithUV(1, pixel*16, 0, 1F/tW*(8+16), 1F/tH*(8));
				t.addVertexWithUV(1, pixel*16, 1, 1F/tW*(8), 1F/tH*(8));
				t.addVertexWithUV(1, 0, 1, 1F/tW*(8), 1F/tH*(8+16));
				
				t.addVertexWithUV(0, 0, 0, 1F/tW*(8+16), 1F/tH*(8+16));
				t.addVertexWithUV(0, 0, 1, 1F/tW*(8), 1F/tH*(8+16));
				t.addVertexWithUV(0, pixel*16, 1, 1F/tW*(8), 1F/tH*(8));
				t.addVertexWithUV(0, pixel*16, 0, 1F/tW*(8+16), 1F/tH*(8));
			}
			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 1) {
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(32), 1F/tH*(32));
				t.addVertexWithUV(0.5, pixel*16, 0, 1F/tW*(32), 1F/tH*(8+16));
				t.addVertexWithUV(0, pixel*16, 0, 1F/tW*(8+16), 1F/tH*(8+16));
				t.addVertexWithUV(0, pixel*16, 0.5, 1F/tW*(8+16), 1F/tH*32);
				
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(32), 1F/tH*(32));
				t.addVertexWithUV(0.5, pixel*0, 0, 1F/tW*(32), 1F/tH*(8+16));
				t.addVertexWithUV(0, pixel*0, 0, 1F/tW*(8+16), 1F/tH*(8+16));
				t.addVertexWithUV(0, pixel*0, 0.5, 1F/tW*(8+16), 1F/tH*32);
				
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(32), 1F/tH*(16));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(32), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*16, 0.5, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*0, 0.5, 1F/tW*(8+16), 1F/tH*16);
				
				t.addVertexWithUV(0.5, pixel*0, 0, 1F/tW*(8), 1F/tH*(16));
				t.addVertexWithUV(0.5, pixel*16, 0, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(0), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(0), 1F/tH*(16));
				
			}			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 2) {
				t.addVertexWithUV(0.5, pixel*16, 1, 1F/tW*(32), 1F/tH*(8+16));
				t.addVertexWithUV(0.5, pixel*16, 0, 1F/tW*(32), 1F/tH*(8));
				t.addVertexWithUV(0, pixel*16, 0, 1F/tW*(8+16), 1F/tH*(8));
				t.addVertexWithUV(0, pixel*16, 1, 1F/tW*(8+16), 1F/tH*(8+16));
				
				t.addVertexWithUV(0.5, pixel*0, 1, 1F/tW*(32), 1F/tH*(8+16));
				t.addVertexWithUV(0.5, pixel*0, 0, 1F/tW*(32), 1F/tH*(8));
				t.addVertexWithUV(0, pixel*0, 0, 1F/tW*(8+16), 1F/tH*(8));
				t.addVertexWithUV(0, pixel*0, 1, 1F/tW*(8+16), 1F/tH*(8+16));
				
				t.addVertexWithUV(0.5, pixel*0, 0, 1F/tW*(8+16), 1F/tH*(16));
				t.addVertexWithUV(0.5, pixel*16, 0, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 1, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 1, 1F/tW*(8), 1F/tH*(16));
				
				
			}
			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 3) {
				t.addVertexWithUV(0.5, pixel*16, 1, 1F/tW*(32), 1F/tH*(8));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(32), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*16, 0.5, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*16, 1, 1F/tW*(8+16), 1F/tH*(8));
				
				t.addVertexWithUV(0.5, pixel*0, 1, 1F/tW*(32), 1F/tH*(8));
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(32), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*0, 0.5, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*0, 1, 1F/tW*(8+16), 1F/tH*(8));
				
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(32), 1F/tH*(16));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(32), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 1, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 1, 1F/tW*(8+16), 1F/tH*(16));
				
				t.addVertexWithUV(0, pixel*0, 0.5, 1F/tW*(8), 1F/tH*(16));
				t.addVertexWithUV(0, pixel*16, 0.5, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(0), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(0), 1F/tH*(16));
				
			}
			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 4) {
				t.addVertexWithUV(1, pixel*16, 0.5, 1F/tW*(8+16), 1F/tH*(32));
				t.addVertexWithUV(1, pixel*16, 0, 1F/tW*(8+16), 1F/tH*(8+16));
				t.addVertexWithUV(0, pixel*16, 0, 1F/tW*(8), 1F/tH*(8+16));
				t.addVertexWithUV(0, pixel*16, 0.5, 1F/tW*(8), 1F/tH*(32));
				
				t.addVertexWithUV(1, pixel*0, 0.5, 1F/tW*(8+16), 1F/tH*(32));
				t.addVertexWithUV(1, pixel*0, 0, 1F/tW*(8+16), 1F/tH*(8+16));
				t.addVertexWithUV(0, pixel*0, 0, 1F/tW*(8), 1F/tH*(8+16));
				t.addVertexWithUV(0, pixel*0, 0.5, 1F/tW*(8), 1F/tH*(32));
				
				t.addVertexWithUV(1, pixel*0, 0.5, 1F/tW*(8+16), 1F/tH*(16));
				t.addVertexWithUV(1, pixel*16, 0.5, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*16, 0.5, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*0, 0.5, 1F/tW*(8), 1F/tH*(16));
				
			}
			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 5) {
				t.addVertexWithUV(1, pixel*16, 1, 1F/tW*(8+16), 1F/tH*(8+16));
				t.addVertexWithUV(1, pixel*16, 0, 1F/tW*(8+16), 1F/tH*8);
				t.addVertexWithUV(0, pixel*16, 0, 1F/tW*8, 1F/tH*8);
				t.addVertexWithUV(0, pixel*16, 1, 1F/tW*8, 1F/tH*(8+16));
				
				t.addVertexWithUV(1, pixel*0, 1, 1F/tW*(8+16), 1F/tH*(8+16));
				t.addVertexWithUV(1, pixel*0, 0, 1F/tW*(8+16), 1F/tH*8);
				t.addVertexWithUV(0, pixel*0, 0, 1F/tW*8, 1F/tH*8);
				t.addVertexWithUV(0, pixel*0, 1, 1F/tW*8, 1F/tH*(8+16));
				
				
			}
			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 6) {
				t.addVertexWithUV(1, pixel*16, 1, 1F/tW*(8+16), 1F/tH*(8));
				t.addVertexWithUV(1, pixel*16, 0.5, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*16, 0.5, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*16, 1, 1F/tW*(8), 1F/tH*(8));
				
				t.addVertexWithUV(1, pixel*0, 1, 1F/tW*(8+16), 1F/tH*(8));
				t.addVertexWithUV(1, pixel*0, 0.5, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*0, 0.5, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0, pixel*0, 1, 1F/tW*(8), 1F/tH*(8));
				
				t.addVertexWithUV(0, pixel*0, 0.5, 1F/tW*(8+16), 1F/tH*(16));
				t.addVertexWithUV(0, pixel*16, 0.5, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(1, pixel*16, 0.5, 1F/tW*(8), 1F/tH*(0));	
				t.addVertexWithUV(1, pixel*0, 0.5, 1F/tW*(8), 1F/tH*(16));
				
			}
			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 7) {
				t.addVertexWithUV(1, pixel*16, 0.5, 1F/tW*(8), 1F/tH*(32));
				t.addVertexWithUV(1, pixel*16, 0, 1F/tW*(8), 1F/tH*(8+16));
				t.addVertexWithUV(0.5, pixel*16, 0, 1F/tW*(0), 1F/tH*(8+16));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(0), 1F/tH*(32));
				
				t.addVertexWithUV(1, pixel*0, 0.5, 1F/tW*(8), 1F/tH*(32));
				t.addVertexWithUV(1, pixel*0, 0, 1F/tW*(8), 1F/tH*(8+16));
				t.addVertexWithUV(0.5, pixel*0, 0, 1F/tW*(0), 1F/tH*(8+16));
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(0), 1F/tH*(32));
				
				t.addVertexWithUV(1, pixel*0, 0.5, 1F/tW*(8), 1F/tH*(16));
				t.addVertexWithUV(1, pixel*16, 0.5, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(0), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(0), 1F/tH*(16));
				
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(32), 1F/tH*(16));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(32), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 0, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 0, 1F/tW*(8+16), 1F/tH*(16));
				
			}
			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 8) {
				t.addVertexWithUV(1, pixel*16, 1, 1F/tW*(8), 1F/tH*(8+16));
				t.addVertexWithUV(1, pixel*16, 0, 1F/tW*(8), 1F/tH*(8));
				t.addVertexWithUV(0.5, pixel*16, 0, 1F/tW*(0), 1F/tH*(8));
				t.addVertexWithUV(0.5, pixel*16, 1, 1F/tW*(0), 1F/tH*(8+16));
				
				t.addVertexWithUV(1, pixel*0, 1, 1F/tW*(8), 1F/tH*(8+16));
				t.addVertexWithUV(1, pixel*0, 0, 1F/tW*(8), 1F/tH*(8));
				t.addVertexWithUV(0.5, pixel*0, 0, 1F/tW*(0), 1F/tH*(8));
				t.addVertexWithUV(0.5, pixel*0, 1, 1F/tW*(0), 1F/tH*(8+16));
				
				t.addVertexWithUV(0.5, pixel*0, 1, 1F/tW*(8+16), 1F/tH*(16));
				t.addVertexWithUV(0.5, pixel*16, 1, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 0, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 0, 1F/tW*(8), 1F/tH*(16));
				
			}
			
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 9) {
				t.addVertexWithUV(1, pixel*16, 1, 1F/tW*(8), 1F/tH*(8));
				t.addVertexWithUV(1, pixel*16, 0.5, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(0), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 01, 1F/tW*(0), 1F/tH*(8));
				
				t.addVertexWithUV(1, pixel*0, 1, 1F/tW*(8), 1F/tH*(8));
				t.addVertexWithUV(1, pixel*0, 0.5, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(0), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 01, 1F/tW*(0), 1F/tH*(8));
				
				t.addVertexWithUV(0.5, pixel*0, 1, 1F/tW*(8), 1F/tH*(16));
				t.addVertexWithUV(0.5, pixel*16, 1, 1F/tW*(8), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(0), 1F/tH*(0));
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(0), 1F/tH*(16));
				
				t.addVertexWithUV(0.5, pixel*0, 0.5, 1F/tW*(32), 1F/tH*(16));
				t.addVertexWithUV(0.5, pixel*16, 0.5, 1F/tW*(32), 1F/tH*(0));
				t.addVertexWithUV(1, pixel*16, 0.5, 1F/tW*(8+16), 1F/tH*(0));
				t.addVertexWithUV(1, pixel*0, 0.5, 1F/tW*(8+16), 1F/tH*(16));
				
			}
		}
		t.draw();
		
		this.bindTexture(textureRestOfWindmill);
		t.startDrawingQuads();
		{
			if(te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 5) {
				if(te.getWorldObj().getBlock(te.xCoord, te.yCoord + 1, te.zCoord).equals(SimpleTech.Windmill)) {
					t.addVertexWithUV(pixel*13, pixel*18, pixel*13, 1, 1);
					t.addVertexWithUV(pixel*13, pixel*18, pixel*3, 1, 0);
					t.addVertexWithUV(pixel*3, pixel*18, pixel*3, 0, 0);
					t.addVertexWithUV(pixel*3, pixel*18, pixel*13, 0, 1);
					
					t.addVertexWithUV(pixel*13, pixel*16, pixel*13, 1, 1F/10F*2);
					t.addVertexWithUV(pixel*13, pixel*18, pixel*13, 1, 0);
					t.addVertexWithUV(pixel*3, pixel*18, pixel*13, 0, 0);
					t.addVertexWithUV(pixel*3, pixel*16, pixel*13, 0, 1F/10F*2);
					
					t.addVertexWithUV(pixel*3, pixel*16, pixel*3, 1, 1F/10F*2);
					t.addVertexWithUV(pixel*3, pixel*18, pixel*3, 1, 0);
					t.addVertexWithUV(pixel*13, pixel*18, pixel*3, 0, 0);
					t.addVertexWithUV(pixel*13, pixel*16, pixel*3, 0, 1F/10F*2);
					
					t.addVertexWithUV(pixel*13, pixel*16, pixel*3, 1, 1F/10F*2);
					t.addVertexWithUV(pixel*13, pixel*18, pixel*3, 1, 0);
					t.addVertexWithUV(pixel*13, pixel*18, pixel*13, 0, 0);
					t.addVertexWithUV(pixel*13, pixel*16, pixel*13, 0, 1F/10F*2);
					
					t.addVertexWithUV(pixel*3, pixel*16, pixel*13, 1, 1F/10F*2);
					t.addVertexWithUV(pixel*3, pixel*18, pixel*13, 1, 0);
					t.addVertexWithUV(pixel*3, pixel*18, pixel*3, 0, 0);
					t.addVertexWithUV(pixel*3, pixel*16, pixel*3, 0, 1F/10F*2);
				}
			}
		}
		t.draw();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);

	}

}
