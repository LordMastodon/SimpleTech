package buildcraft.api.power;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public interface IPowerReceptor {
	
	public PowerHandler.PowerReceiver getPowerReceiver(ForgeDirection side);

	public void doWork(PowerHandler workProvider);

	public World getWorld();
}