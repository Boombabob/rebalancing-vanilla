package boombabob.rebalancingVanilla.mixin;

import boombabob.rebalancingVanilla.RebalancingVanilla;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "travelControlled", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;travel(Lnet/minecraft/util/math/Vec3d;)V", shift = At.Shift.BEFORE))
    private void fakeSwim(PlayerEntity controllingPlayer, Vec3d movementInput, CallbackInfo ci) {
        if (RebalancingVanilla.CONFIG.horsesSwim && ((LivingEntity)(Object)this instanceof AbstractHorseEntity)) {
            AbstractHorseEntity instance = ((AbstractHorseEntity)(Object)this);
            if (instance.getFluidHeight(FluidTags.WATER) > instance.getSwimHeight()) {
                instance.addVelocity(0, 0.06, 0);
            }
        }
    }

    @ModifyArg(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V", ordinal = 6))
    private Vec3d slowElytra(Vec3d vec3d) {
        return vec3d.multiply(RebalancingVanilla.CONFIG.elytraSpeedMultiplier, 1, RebalancingVanilla.CONFIG.elytraSpeedMultiplier);
    }
}
