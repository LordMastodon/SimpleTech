package lordmastodon.simpletech;

import lordmastodon.simpletech.tileentity.TileEntityMWCable;
import lordmastodon.simpletech.tileentity.TileEntityRenderMWCable;
import lordmastodon.simpletech.tileentity.TileEntityRenderWindmill;
import lordmastodon.simpletech.tileentity.TileEntityRenderWindmillGround;
import lordmastodon.simpletech.tileentity.TileEntityWindmill;
import lordmastodon.simpletech.tileentity.TileEntityWindmillFloor;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	
	public void renderStuff() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new TileEntityRenderWindmill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmillFloor.class, new TileEntityRenderWindmillGround());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMWCable.class, new TileEntityRenderMWCable());
	}

}
