package boombabob.rebalancingVanilla.mixin;

import boombabob.rebalancingVanilla.RebalancingVanilla;
import net.minecraft.block.Blocks;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AbstractHorseEntity.class)
public abstract class AbstractHorseEntityMixin {
    /**
     * @author Boombabob
     * @reason Buff horse
     */
    @Overwrite
    public float getSaddledSpeed(PlayerEntity controllingPlayer) {
        AbstractHorseEntity instance = ((AbstractHorseEntity) (Object) this);
        double movementSpeed = instance.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (RebalancingVanilla.CONFIG.horseSwimAmount > 0 && instance.getFluidHeight(FluidTags.WATER) > instance.getSwimHeight()) {
            instance.addVelocity(0, RebalancingVanilla.CONFIG.horseSwimAmount, 0);
        }
        if (instance.getWorld().getBlockState(instance.getBlockPos().down()).getBlock() == Blocks.DIRT_PATH) {
            movementSpeed *= RebalancingVanilla.CONFIG.horseDirtPathSpeedMultiplier;
        } else {
            movementSpeed *= RebalancingVanilla.CONFIG.horseSpeedMultiplier;
        }
        return (float) movementSpeed;
    }
}