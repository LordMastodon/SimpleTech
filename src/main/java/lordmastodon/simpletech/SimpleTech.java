package lordmastodon.simpletech;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import lordmastodon.simpletech.block.*;
import lordmastodon.simpletech.creativetab.CreativeTabSimpleTech;
import lordmastodon.simpletech.event.STWindmillHighlightEvent;
import lordmastodon.simpletech.handler.GuiHandler;
import lordmastodon.simpletech.item.FailedAlloy;
import lordmastodon.simpletech.item.MaceratedPowder;
import lordmastodon.simpletech.item.WindmillItem;
import lordmastodon.simpletech.tileentity.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = SimpleTech.MODID, version = SimpleTech.VERSION, name = SimpleTech.NAME)
public class SimpleTech {
	
	@SidedProxy(clientSide = "lordmastodon.simpletech.ClientProxy", serverSide = "lordmastodon.simpletech.CommonProxy")
	public static CommonProxy proxy;
	
    public static final String MODID = "st";
    public static final String VERSION = "Alpha 1.00";
    public static final String NAME = "SimpleTech";
    
    @Instance("st")
    public static SimpleTech instance;
       
    public static CreativeTabs SimpleTab = new CreativeTabSimpleTech("SimpleTechTab");
    
    public static Block Windmill;
    public static Item WindmillItem;
    public static Block WindmillGround;
    public static Block AlloyFurnaceIdle;
    public static Block AlloyFurnaceActive;
    public static Block MWCable;
    public static Item failedAlloy;
    public static Block MaceratorIdle;
    public static Block MaceratorActive;

    public static final int LiquidTankGUIid = 0;
    public static final int ElectricFurnaceGUIid = 1;
    public static final int AlloyFurnaceGUIid = 2;
    public static final int GeneratorGUIid = 3;
    public static final int AutoCrafterGUIid = 5;
        
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	AlloyFurnaceIdle = new AlloyFurnace(false).setBlockName("AlloyFurnaceIdle").setCreativeTab(SimpleTab);
    	AlloyFurnaceActive = new AlloyFurnace(true).setBlockName("AlloyFurnaceActive").setLightLevel(0.5F);
    	Windmill = new Windmill(Material.ground).setBlockName("Windmill");
    	WindmillItem = new WindmillItem().setUnlocalizedName("WindmillItem").setCreativeTab(SimpleTab).setTextureName(MODID + ":WindmillItem");
    	WindmillGround = new WindmillGround(Material.ground).setBlockName("WindmillGround").setBlockTextureName(MODID + ":WindmillGround").setCreativeTab(SimpleTab);
    	failedAlloy = new FailedAlloy().setUnlocalizedName("FailedAlloy");
    	MWCable = new MWCable().setBlockName("MWCable").setCreativeTab(SimpleTab);
        MaceratorIdle = new Macerator(false).setBlockName("MaceratorIdle").setCreativeTab(SimpleTab);
        MaceratorActive = new Macerator(true).setBlockName("MaceratorActive").setCreativeTab(SimpleTab);
    	    	
    	GameRegistry.registerBlock(Windmill, "Windmill");
    	GameRegistry.registerItem(WindmillItem, "WindmillItem");
    	GameRegistry.registerBlock(WindmillGround, "WindmillGround");
    	GameRegistry.registerBlock(AlloyFurnaceActive, "AlloyFurnaceActive");
    	GameRegistry.registerBlock(AlloyFurnaceIdle, "AlloyFurnaceIdle");
    	GameRegistry.registerBlock(MWCable, "MWCable");
        GameRegistry.registerBlock(MaceratorIdle, "MaceratorIdle");
        GameRegistry.registerBlock(MaceratorActive, "MaceratorActive");
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    	    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.renderStuff();        
        
        GameRegistry.registerTileEntity(TileEntityWindmill.class, "TEW");
        GameRegistry.registerTileEntity(TileEntityWindmillFloor.class, "TEWF");
        GameRegistry.registerTileEntity(TileEntityMWCable.class, "TETMWC");
        GameRegistry.registerTileEntity(TileEntityAlloyFurnace.class, "TileEntityAlloyFurnace");
        GameRegistry.registerTileEntity(TileEntityMacerator.class, "TileEntityMacerator");


        MinecraftForge.EVENT_BUS.register(new STWindmillHighlightEvent());
    	
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.renderStuff();
    }

}