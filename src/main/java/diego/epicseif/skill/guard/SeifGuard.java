package diego.epicseif.skill.guard;


import java.util.UUID;

import diego.epicseif.SeifForEpicFight;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import diego.epicseif.gameasset.SeifAnimations;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.guard.GuardSkill;
import static diego.epicseif.world.capabilities.item.SeifWeaponCategories.SEIF;


@Mod.EventBusSubscriber(modid = SeifForEpicFight.MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)

public class SeifGuard extends Skill {
    public static final UUID EVENT_UUID = UUID.fromString("b422f7a0-f378-11eb-9a03-0242ac703459");
    public SeifGuard(Builder<? extends Skill> builder) {
        super(builder);
    }

    public static Builder createGuardBuilder() {
        return (new GuardSkill.Builder()).setCategory(SkillCategories.GUARD).setActivateType(ActivateType.ONE_SHOT).setResource(Resource.STAMINA)
                .addGuardMotion(SEIF, (item, player) -> {
                    return SeifAnimations.SEIF_GUARD;
                })
                .addGuardBreakMotion(SEIF, (item, player) -> {
                    return Animations.BIPED_COMMON_NEUTRALIZED;
                });
    }
    public static void registerGuard(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(SeifForEpicFight.MOD_ID,"seif"), SEIF);
    }
}