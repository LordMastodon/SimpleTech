package buildcraft.api.power;

import net.minecraftforge.common.util.ForgeDirection;

public interface IPowerEmitter {

	public boolean canEmitPowerFrom(ForgeDirection side);
}