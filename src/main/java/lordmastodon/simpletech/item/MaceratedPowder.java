package lordmastodon.simpletech.item;

import cpw.mods.fml.common.registry.GameRegistry;
import lordmastodon.simpletech.SimpleTech;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Nate on 8/5/2014.
 */
public class MaceratedPowder extends Item {

    private static final MaceratedPowder maceratingBase = new MaceratedPowder();

    public static MaceratedPowder macerating() {
        return maceratingBase;
    }

    public static int j = 0;

    public static Map maceratingList = new HashMap();

    public static String[] oreNames = OreDictionary.getOreNames();

    public static void checkForOres(ItemStack is) {
        for (int i = 0; i < oreNames.length; i++) {
            j = i;

            if (oreNames[i].contains("ore")) {
                if (OreDictionary.getOres(oreNames[i]) != null) {
                    if (OreDictionary.getOres(oreNames[i]).get(0).getItem() == is.getItem()) {
                        powders[i] = new Item().setCreativeTab(SimpleTech.SimpleTab).setUnlocalizedName("Powdered" + oreNames[i]);
                    }
                }
            }
        }
    }

    public static Item[] powders = new Item[j];

    public static void addNewPowdersAndRecipes() {

        for (int i = 0; i < powders.length; i++) {
            GameRegistry.registerItem(powders[i], "Powdered" + powders[i].getUnlocalizedName());

            addNewBlockRecipe(OreDictionary.getOres(oreNames[i]), new ItemStack(powders[i]));
        }

    }

    public static void addNewBlockRecipe(ArrayList<ItemStack> is, ItemStack is1) {
        maceratingList.put(is, is1);
    }

    public static ItemStack getMaceratingResult(ItemStack is)
    {
        Iterator iterator = maceratingList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!IReallyDontKnow(is, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private static boolean IReallyDontKnow(ItemStack is, ItemStack is1)
    {
        return is1.getItem() == is.getItem() && (is1.getItemDamage() == 32767 || is1.getItemDamage() == is.getItemDamage());
    }

    public static Map getMaceratingList()
    {
        return maceratingList;
    }
}