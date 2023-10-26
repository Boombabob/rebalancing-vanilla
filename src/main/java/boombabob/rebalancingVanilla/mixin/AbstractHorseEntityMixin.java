package boombabob.rebalancingVanilla.mixin;

import boombabob.rebalancingVanilla.RebalancingVanilla;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
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
        BlockPos feetPos = ((AbstractHorseEntity) (Object) this).getBlockPos().down();
        Block blockAtFeet = controllingPlayer.getWorld().getBlockState(feetPos).getBlock();
        float horseSpeedMultiplier;
        if (blockAtFeet == Blocks.DIRT_PATH) {
            horseSpeedMultiplier = RebalancingVanilla.CONFIG.horseDirtPathSpeedMultiplier;
        } else {
            horseSpeedMultiplier = RebalancingVanilla.CONFIG.horseSpeedMultiplier;
        }
        return (float) ((AbstractHorseEntity) (Object) this).getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) * horseSpeedMultiplier;
    }

}