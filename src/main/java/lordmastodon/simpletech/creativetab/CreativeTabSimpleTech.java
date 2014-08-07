package lordmastodon.simpletech.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabSimpleTech extends CreativeTabs {

	public CreativeTabSimpleTech(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return Items.iron_ingot;
	}

}
