package lordmastodon.simpletech.handler;

import lordmastodon.simpletech.container.ContainerAlloyFurnace;
import lordmastodon.simpletech.container.ContainerMacerator;
import lordmastodon.simpletech.gui.GuiAlloyFurnace;
import lordmastodon.simpletech.gui.GuiMacerator;
import lordmastodon.simpletech.tileentity.TileEntityAlloyFurnace;
import lordmastodon.simpletech.tileentity.TileEntityMacerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity entity = world.getTileEntity(x, y, z);
    if (entity != null) {
      switch (ID) {
      case 0: 
        if ((entity instanceof TileEntityAlloyFurnace)) {
          return new ContainerAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)entity);
        }
      case 1:
        if ((entity instanceof TileEntityMacerator)) {
            return new ContainerMacerator(player.inventory, (TileEntityMacerator)entity);
        }
        break;
      }
    }
    return null;
  }
  
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity entity = world.getTileEntity(x, y, z);
    if (entity != null) {
      switch (ID) {
      case 0: 
        if ((entity instanceof TileEntityAlloyFurnace)) {
          return new GuiAlloyFurnace(player.inventory, (TileEntityAlloyFurnace)entity);
        }
      case 1:
        if ((entity instanceof TileEntityMacerator)) {
            return new GuiMacerator(player.inventory, (TileEntityMacerator)entity);
        }
        break;
      }
    }
    return null;
  }
}
