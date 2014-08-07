package lordmastodon.simpletech.gui;

import lordmastodon.simpletech.SimpleTech;
import lordmastodon.simpletech.container.ContainerAlloyFurnace;
import lordmastodon.simpletech.tileentity.TileEntityAlloyFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiAlloyFurnace extends GuiContainer {
  public static final ResourceLocation texture = new ResourceLocation(SimpleTech.MODID, "textures/gui/AlloyFurnace.png");
  public TileEntityAlloyFurnace alloyFurnace;
  
  public GuiAlloyFurnace(InventoryPlayer inventoryPlayer, TileEntityAlloyFurnace entity)
  {
    super(new ContainerAlloyFurnace(inventoryPlayer, entity));
    
    this.alloyFurnace = entity;
    
    this.xSize = 176;
    this.ySize = 166;
  }
  
  public void drawGuiContainerForegroundLayer(int par1, int par2)
  {
    String name = this.alloyFurnace.hasCustomInventoryName() ? this.alloyFurnace.getInventoryName() : I18n.format(this.alloyFurnace.getInventoryName(), new Object[0]);
    
    this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
    this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
  }
  
  protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
    drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    if (this.alloyFurnace.isBurning())
    {
      int k = this.alloyFurnace.getBurnTimeRemainingScaled(12);
      drawTexturedModalRect(this.guiLeft + 33, this.guiTop + 36 + 12 - k, 176, 12 - k, 14, k + 2);
    }
    int k = this.alloyFurnace.getCookProgressScaled(24);
    drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 34, 176, 14, k + 1, 16);
  }
}
