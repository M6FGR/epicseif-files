package diego.epicseif.world.capabilities.item;


import net.minecraft.world.item.Item;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.function.Function;

public enum SeifWeaponCategories implements yesman.epicfight.world.capabilities.item.WeaponCategory, Function<Item, CapabilityItem.Builder> {
    SEIF;

    final int id;

    SeifWeaponCategories() {
        this.id = yesman.epicfight.world.capabilities.item.WeaponCategory.ENUM_MANAGER.assign(this);
    }

    @Override
    public int universalOrdinal() {
        return this.id;
    }

    @Override
    public CapabilityItem.Builder apply(Item item) {
        return diego.epicseif.world.capabilities.item.WeaponCategoryMapper.apply(item, this);
    }

    public static class Builder {
    }
}