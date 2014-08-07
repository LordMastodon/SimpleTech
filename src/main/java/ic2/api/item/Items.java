package ic2.api.item;

import net.minecraft.item.ItemStack;

public final class Items
{
	/**
	 * Get an ItemStack for a specific item name, example: Items.getItem("resin")
	 * See the list below for item names.
	 * Make sure to copy() the ItemStack if you want to modify it.
	 * 
	 * @param name item name
	 * @return The item or null if the item does not exist or an error occurred
	 */
	public static ItemStack getItem(String name)
	{
		try
		{
			if (Ic2Items == null)
				Ic2Items = Class.forName(getPackage() + ".core.Ic2Items");

			Object ret = Ic2Items.getField(name).get(null);

			if (ret instanceof ItemStack)
			{
				return (ItemStack) ret;
			}
			return null;
		}
		catch (Exception e)
		{
			System.out.println("IC2 API: Call getItem failed for " + name);

			return null;
		}
	}

	/**
	 * Get the base IC2 package name, used internally.
	 * 
	 * @return IC2 package name, if unable to be determined defaults to ic2
	 */
	private static String getPackage()
	{
		Package pkg = Items.class.getPackage();

		if (pkg != null)
		{
			String packageName = pkg.getName();

			return packageName.substring(0, packageName.length() - ".api.item".length());
		}

		return "ic2";
	}

	private static Class<?> Ic2Items;
}