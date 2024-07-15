package diego.epicseif.gameasset;

import diego.epicseif.SeifForEpicFight;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.MoveCoordFunctions;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.HitEntityList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.StunType;

import static yesman.epicfight.api.animation.property.AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED;


public class SeifAnimations {

    public static StaticAnimation SEIF_AIRSLASH;
    public static StaticAnimation SEIF_AUTO1;
    public static StaticAnimation SEIF_AUTO2;
    public static StaticAnimation SEIF_AUTO3;
    public static StaticAnimation SEIF_INNER1;
    public static StaticAnimation SEIF_INNER2;
    public static StaticAnimation SEIF_INNER3;
    public static StaticAnimation SEIF_INNER_ACTIVE;
    public static StaticAnimation SEIF_DASH;
    public static StaticAnimation SEIF_GUARD;
    public static StaticAnimation SEIF_GUARD_HIT;
    public static StaticAnimation BIPED_SEIF_IDLE;
    public static StaticAnimation BIPED_SEIF_INNER_IDLE;
    public static StaticAnimation BIPED_SEIF_WALK;
    public static StaticAnimation BIPED_SEIF_INNER_WALK;
    public static StaticAnimation BIPED_SEIF_RUN;
    public static StaticAnimation BIPED_SEIF_INNER_RUN;
    public static StaticAnimation SEIF_INNER_DASH;
    public static StaticAnimation SEIF_INNER_AIRSLASH;

    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(SeifForEpicFight.MOD_ID, SeifAnimations::build);
    }

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;


        SEIF_AIRSLASH = new AirSlashAnimation(0.1F, 0.2F, 0.5F, 0.5F, null, biped.handR, "biped/combat/seif_airslash", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(BASIS_ATTACK_SPEED, 1.2F);

        SEIF_INNER_AIRSLASH = new AirSlashAnimation(0.1F, 0.2F, 0.5F, 0.5F, null, biped.handR, "biped/combat/seif_airslash", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(BASIS_ATTACK_SPEED, 1.2F);

        SEIF_AUTO1 = new BasicAttackAnimation(0.1F, 0.2F, 0.9F, 0.5F, null, biped.toolR, "biped/combat/seif_auto1", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get());

        SEIF_AUTO2 = new BasicAttackAnimation(0.1F, 0.2F, 0.9F, 0.5F, null, biped.toolR, "biped/combat/seif_auto2", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get());

        SEIF_AUTO3 = new BasicAttackAnimation(0.1F, 0.2F, 0.9F, 0.5F, null, biped.toolR, "biped/combat/seif_auto3", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get());

        SEIF_INNER_ACTIVE = new AttackAnimation(0.05F, "biped/skill/seif_inner_active", biped,
                new AttackAnimation.Phase(0.0F, 0.2F, 0.1F, 0.2F, 1.4F, 1.7F, biped.rootJoint, null));

        SEIF_INNER1 = new BasicAttackAnimation(0.05F, "biped/skill/seif_inner1", biped,
                new AttackAnimation.Phase(0.0F, 0.3F, 0.1F, 0.2F, 0.4F, 0.4F, biped.elbowL, null)
                , new AttackAnimation.Phase(0.1F, 0.3F, 0.5F, 0.6F, 0.7F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F))
                .addProperty(BASIS_ATTACK_SPEED, 1.0F).addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG);

        SEIF_INNER2 = new BasicAttackAnimation(0.05F, "biped/skill/seif_inner2", biped,
                new AttackAnimation.Phase(0.0F, 0.7F, 0.1F, 0.2F, 0.4F, 0.4F, biped.legR, null)
                , new AttackAnimation.Phase(0.1F, 0.3F, 0.5F, 0.6F, 0.7F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F))
                .addProperty(BASIS_ATTACK_SPEED, 1.0F).addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG);

        SEIF_INNER3 = new DashAttackAnimation(0.15F, "biped/skill/seif_inner3", biped, new AttackAnimation.Phase(0.0F, 0.3F, 0.8F, 0.9F, 1.2F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                .addProperty(BASIS_ATTACK_SPEED, 1.6F);

        SEIF_DASH = new DashAttackAnimation(0.15F, "biped/combat/seif_dash", biped, new AttackAnimation.Phase(0.0F, 0.3F, 0.8F, 0.9F, 1.2F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                .addProperty(BASIS_ATTACK_SPEED, 1.6F);

        SEIF_INNER_DASH = new DashAttackAnimation(0.15F, "biped/skill/seif_inner_dash", biped, new AttackAnimation.Phase(0.0F, 0.3F, 0.8F, 0.9F, 1.2F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                .addProperty(BASIS_ATTACK_SPEED, 1.6F);

        SEIF_GUARD = new StaticAnimation(true, "biped/skill/seif_guard", biped);
        SEIF_GUARD_HIT = new GuardAnimation(0.05F, "biped/skill/seif_guard_hit", biped);

        BIPED_SEIF_IDLE = new StaticAnimation(true, "biped/living/seif_idle", biped);
        BIPED_SEIF_INNER_IDLE = new StaticAnimation(true, "biped/skill/seif_inner_idle", biped);
        BIPED_SEIF_WALK = new MovementAnimation(true, "biped/living/seif_walk", biped);
        BIPED_SEIF_INNER_WALK = new StaticAnimation(true, "biped/skill/seif_inner_walk", biped);
        BIPED_SEIF_RUN = new MovementAnimation(true, "biped/living/seif_run", biped);
        BIPED_SEIF_INNER_RUN = new StaticAnimation(true, "biped/skill/seif_inner_run", biped);

    }
}