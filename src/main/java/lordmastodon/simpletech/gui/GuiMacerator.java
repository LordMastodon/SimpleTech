package lordmastodon.simpletech.gui;

import lordmastodon.simpletech.SimpleTech;
import lordmastodon.simpletech.container.ContainerMacerator;
import lordmastodon.simpletech.tileentity.TileEntityMacerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Nate on 8/7/2014.
 */
public class GuiMacerator extends GuiContainer {
    public static final ResourceLocation texture = new ResourceLocation(SimpleTech.MODID, "textures/gui/Macerator.png");

    public TileEntityMacerator macerator;

    public GuiMacerator(InventoryPlayer invPlayer, TileEntityMacerator entity) {
        super(new ContainerMacerator(invPlayer, entity));

        this.macerator = entity;

        this.xSize = 176;
        this.ySize = 165;
    }

    public void drawGuiContainerForegroundLayer(int par1, int par2){
        String name = this.macerator.isInvNameLocalized() ? this.macerator.getInvName() : I18n.format(this.macerator.getInvName());

        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    public void drawGuiContainerBackgroundLayer(float f, int j, int i) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int i1;

        if(this.macerator.hasPower()){
            i1 = this.macerator.getPowerRemainingScaled(16);
            this.drawTexturedModalRect(guiLeft + 56 - i1, guiTop + 43, 191 - i1, 0, i1, 16);
        }

        i1 = this.macerator.getCookProgressScaled(24);
        this.drawTexturedModalRect(guiLeft + 79, guiTop + 42, 176, 16, i1 + 1, 16);
    }
}
