package diego.epicseif.world.capabilities.item;

import java.util.Map;
import java.util.function.Function;
import com.google.common.collect.Maps;
import diego.epicseif.SeifForEpicFight;
import diego.epicseif.gameasset.SeifSkills;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import diego.epicseif.gameasset.SeifColliderPreset;
import diego.epicseif.gameasset.SeifAnimations;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.WeaponCapability;


@Mod.EventBusSubscriber(modid = SeifForEpicFight.MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> SEIF = WeaponCapabilityPresets::apply;

    public WeaponCapabilityPresets() {
    }

    private static boolean CheckPlayer(LivingEntityPatch<?> playerPatch) {
        return playerPatch.getOriginal().getType() != EntityType.PLAYER;
    }

    private static final Map<String, Function<Item, CapabilityItem.Builder>> PRESETS = Maps.newHashMap();

    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(SeifForEpicFight.MOD_ID,"seif"), SEIF);
    }

    private static CapabilityItem.Builder apply(Item item) {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .styleProvider((playerpatch) -> Styles.ONE_HAND)
                .category(CapabilityItem.WeaponCategories.TACHI)
                .collider(SeifColliderPreset.SEIF)
                .swingSound(EpicFightSounds.WHOOSH.get())
                .hitSound(EpicFightSounds.BLUNT_HIT.get())
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.ONE_HAND, SeifAnimations.SEIF_AUTO1, SeifAnimations.SEIF_AUTO2, SeifAnimations.SEIF_AUTO3, SeifAnimations.SEIF_DASH, SeifAnimations.SEIF_AIRSLASH)
                .newStyleCombo(Styles.TWO_HAND, SeifAnimations.SEIF_INNER1, SeifAnimations.SEIF_INNER2, SeifAnimations.SEIF_INNER3, SeifAnimations.SEIF_INNER_DASH, SeifAnimations.SEIF_INNER_AIRSLASH)
                .innateSkill(Styles.TWO_HAND, (itemstack) -> SeifSkills.ACTIVATEGUARD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.IDLE, SeifAnimations.BIPED_SEIF_IDLE)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.WALK, SeifAnimations.BIPED_SEIF_WALK)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.CHASE, SeifAnimations.BIPED_SEIF_WALK)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.RUN, SeifAnimations.BIPED_SEIF_RUN)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.JUMP, SeifAnimations.BIPED_SEIF_RUN)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.KNEEL, SeifAnimations.BIPED_SEIF_WALK)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SNEAK, SeifAnimations.BIPED_SEIF_WALK)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SWIM, SeifAnimations.BIPED_SEIF_WALK)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.BLOCK, SeifAnimations.SEIF_GUARD);

        return builder;
    }
}