package boombabob.rebalancingVanilla.mixin;

import boombabob.rebalancingVanilla.RebalancingVanilla;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(FurnaceMinecartEntity.class)
public class FurnaceMinecartEntityMixin {
    /**
     * @author Boombabob
     * @reason Buff furnace minecart
     */
    @Overwrite
    public double getMaxSpeed() {
        return ((((FurnaceMinecartEntity)(Object)this).isTouchingWater() ? 3.0 : 4.0) / 20.0) * RebalancingVanilla.CONFIG.furnaceMinecartSpeedMultiplier;
    }
}
