package boombabob.rebalancingVanilla.mixin;

import boombabob.rebalancingVanilla.RebalancingVanilla;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/loot/function/ApplyBonusLootFunction$OreDrops")
public class OreDropsMixin {
    @Inject(method = "getValue", at = @At(value = "RETURN"), cancellable = true)
    private void injected(Random random, int initialCount, int enchantmentLevel, CallbackInfoReturnable<Integer> cir) {
        int returnValue = (int) (cir.getReturnValue() * 0.75f);
        if (returnValue < 1) {
            returnValue = 1;
        }
        cir.setReturnValue(returnValue);
    }
}
