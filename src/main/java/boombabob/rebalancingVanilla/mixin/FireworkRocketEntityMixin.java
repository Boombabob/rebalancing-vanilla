package boombabob.rebalancingVanilla.mixin;

import boombabob.rebalancingVanilla.RebalancingVanilla;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireworkRocketEntity.class)
public class FireworkRocketEntityMixin {
    @Shadow
    private int lifeTime;

    @Unique
    private int FlightDuration = 1;
    @Inject(method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)V", at = @At("TAIL"))
    private void injected(World world, double x, double y, double z, ItemStack stack, CallbackInfo ci) {
        if (RebalancingVanilla.CONFIG.fireworkLevelChangesBoostNotDuration) {
            if (!stack.isEmpty() && stack.hasNbt()) {
                FlightDuration = stack.getOrCreateSubNbt("Fireworks").getByte("Flight");
            }
            Random random = Random.create();
            this.lifeTime = 10 + random.nextInt(6) + random.nextInt(7);
        }
        if (RebalancingVanilla.CONFIG.fireworkFlightTimeDivisor != 0) {
            this.lifeTime = (int) (this.lifeTime / RebalancingVanilla.CONFIG.fireworkFlightTimeDivisor);
        }
    }
    @Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"))
    private void decreaseVelocity(LivingEntity instance, Vec3d vec3d3) {
        Vec3d vec3d2 = instance.getVelocity();
        Vec3d vec3d = instance.getRotationVector();
        // No idea what the numbers actually mean, but I made it work
        double x = 0.5d;
        float fireworkBoostStrengthMultiplier = RebalancingVanilla.CONFIG.fireworkBoostStrengthMultiplier;
        if (RebalancingVanilla.CONFIG.fireworkLevelChangesBoostNotDuration) {
            fireworkBoostStrengthMultiplier *= FlightDuration;
        }
        if (RebalancingVanilla.CONFIG.fireworkBoostStrengthMultiplier != 0) {
            instance.setVelocity(vec3d2.add(vec3d.multiply(x * fireworkBoostStrengthMultiplier + 0.1).subtract(vec3d2.multiply(x))));
        }
    }

}
