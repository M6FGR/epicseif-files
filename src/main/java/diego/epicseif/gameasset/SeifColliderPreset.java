package diego.epicseif.gameasset;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import diego.epicseif.SeifForEpicFight;
import net.minecraft.resources.ResourceLocation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;

public class SeifColliderPreset {

    private static final BiMap<ResourceLocation, Collider> PRESETS = HashBiMap.create();

    public static Collider registerCollider(ResourceLocation rl, Collider collider) {
        if (PRESETS.containsKey(rl)) {
            throw new IllegalStateException("Collider named " + rl + " already registered.");
        }
        PRESETS.put(rl, collider);

        return collider;
    }

    public static final Collider SEIF = registerCollider(new ResourceLocation(SeifForEpicFight.MOD_ID, "seif"), new MultiOBBCollider(3, 0.7D, 0.9D, 0.7D, 0D, 0.1D, 0.0D));
}