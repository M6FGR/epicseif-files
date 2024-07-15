package diego.epicseif.world.capabilities.item;

import net.minecraft.world.item.Item;
import yesman.epicfight.world.capabilities.item.WeaponCategory;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class WeaponCategoryMapper {
    private static final Map<SeifWeaponCategories, WeaponCategory> categoryMap = new HashMap<>();

    static {
        categoryMap.put(SeifWeaponCategories.SEIF, CapabilityItem.WeaponCategories.TACHI);
    }

    public static CapabilityItem.Builder apply(Item item, SeifWeaponCategories category) {
        WeaponCategory mappedCategory = categoryMap.getOrDefault(category, category);

        try {
            Method applyMethod = mappedCategory.getClass().getMethod("apply", Item.class);
            return (CapabilityItem.Builder) applyMethod.invoke(mappedCategory, item);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}