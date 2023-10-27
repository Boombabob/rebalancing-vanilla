package boombabob.rebalancingVanilla.mixin;

import boombabob.rebalancingVanilla.RebalancingVanilla;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @ModifyArg(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V", ordinal = 6))
    private Vec3d modifyElytraFlight(Vec3d vec3d) {
        LivingEntity instance = ((LivingEntity)(Object) this);
        BlockPos instancePos = instance.getBlockPos();
        if (RebalancingVanilla.CONFIG.campfireUpdraftStrength > 0) {
            for (int i = 0; i < Math.max(RebalancingVanilla.CONFIG.campfireUpdraftHeight, RebalancingVanilla.CONFIG.signalCampfireUpdraftHeight); i++) {
                BlockState blockIBelow = instance.getWorld().getBlockState(instancePos.down(i));
                if (CampfireBlock.isLitCampfire(blockIBelow)) {
                    int maxDistance;
                    if (blockIBelow.get(Properties.SIGNAL_FIRE)) {
                        maxDistance = RebalancingVanilla.CONFIG.signalCampfireUpdraftHeight;
                    } else {
                        maxDistance = RebalancingVanilla.CONFIG.campfireUpdraftHeight;
                    }
                    if (i > maxDistance) {
                        break;
                    }
                    if (blockIBelow.getBlock() == Blocks.SOUL_CAMPFIRE) {
                        vec3d = vec3d.add(0, RebalancingVanilla.CONFIG.soulCampfireUpdraftStrength / 10, 0);
                    } else {
                        vec3d = vec3d.add(0, RebalancingVanilla.CONFIG.campfireUpdraftStrength / 10, 0);
                    }
                    break;
                } else if (!blockIBelow.isAir()) {
                    break;
                }
            }
        }
        return vec3d.multiply(RebalancingVanilla.CONFIG.elytraSpeedMultiplier, 1, RebalancingVanilla.CONFIG.elytraSpeedMultiplier);
    }
}
