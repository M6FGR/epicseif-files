package diego.epicseif.gameasset;


import diego.epicseif.SeifForEpicFight;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.data.reloader.SkillManager;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import diego.epicseif.skill.weaponinnate.ActivateGuardSkill;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

import java.util.Set;


@Mod.EventBusSubscriber(
        modid = SeifForEpicFight.MOD_ID,
        bus= Mod.EventBusSubscriber.Bus.FORGE
)
public class SeifSkills {
    public static Skill ACTIVATEGUARD;

    public SeifSkills(){
    }

    public static void registerSkills(){
        SkillManager.register(ActivateGuardSkill::new, WeaponInnateSkill.createWeaponInnateBuilder(), "epicseif", "activate_guard");
    }

    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent onBuild) {

        WeaponInnateSkill ActivateGuardSkill = (WeaponInnateSkill) onBuild.build(SeifForEpicFight.MOD_ID, "activate_guard");
        ActivateGuardSkill.newProperty()
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.2F))
                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(8.0F))
                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0])))
                .newProperty()
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.0F))
                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(10.0F))
                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create(new float[0])));
        ACTIVATEGUARD = ActivateGuardSkill;
    }


}


