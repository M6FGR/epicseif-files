package diego.epicseif;

import com.mojang.logging.LogUtils;
import diego.epicseif.gameasset.SeifAnimations;
import diego.epicseif.skill.weaponinnate.ActivateGuardSkill;
import diego.epicseif.world.capabilities.item.SeifWeaponCategories;
import diego.epicseif.world.item.SeifAddonItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import yesman.epicfight.world.capabilities.item.WeaponCategory;


@Mod("epicseif")
public class SeifForEpicFight {
    public static final String MOD_ID = "epicseif";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SeifForEpicFight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        WeaponCategory.ENUM_MANAGER.registerEnumCls("epicseif", SeifWeaponCategories.class);
        ActivateGuardSkill.createBuilder();
        SeifAddonItem.ITEMS.register(bus);
        bus.addListener(SeifAnimations::registerAnimations);
        bus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void clientSetup(Event event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(SeifAddonItem.ALMAS_SEIF);
            event.accept(SeifAddonItem.ASWAD_SEIF);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}

