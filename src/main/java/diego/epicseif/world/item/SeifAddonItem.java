package diego.epicseif.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import diego.epicseif.world.item.SiefItem;
public class SeifAddonItem {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item>  ASWAD_SEIF;
    public static final RegistryObject<Item> ALMAS_SEIF;

    public SeifAddonItem() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "epicseif");

        ALMAS_SEIF = ITEMS.register("almas_seif", () -> {
            return new SiefItem(new Item.Properties(), Tiers.DIAMOND);
        });
        ASWAD_SEIF = ITEMS.register("aswad_seif", () -> {
            return new SiefItem((new Item.Properties()).requiredFeatures(), Tiers.NETHERITE);
        });
    }
}

