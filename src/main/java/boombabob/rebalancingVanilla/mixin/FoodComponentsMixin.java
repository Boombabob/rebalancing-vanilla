package boombabob.rebalancingVanilla.mixin;

import boombabob.rebalancingVanilla.RebalancingVanilla;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FoodComponents.class)
public class FoodComponentsMixin {
    @Final
    @Shadow
    public static FoodComponent COOKIE = makeFood(2, 0.1f, true).build();

    @Final
    @Shadow
    public static FoodComponent SWEET_BERRIES = makeFood(2, 0.1f, true).build();

    @Final
    @Shadow
    public static FoodComponent GLOW_BERRIES = RebalancingVanilla.CONFIG.glowBerriesGlow ?
        makeFood(2, 0.1f, true).statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0), 1.0f).alwaysEdible().build()
        : makeFood(2, 0.1f, true).build();

    @Final
    @Shadow
    public static FoodComponent MELON_SLICE = makeFood(2, 0.3f, true).build();

    @Final
    @Shadow
    public static FoodComponent CHORUS_FRUIT = makeFood(4, 0.3f, true).alwaysEdible().build();

    @Final
    @Shadow
    public static FoodComponent CARROT = makeFood(3, 0.3f, true).build();

    @Final
    @Shadow
    public static FoodComponent GOLDEN_CARROT = makeFood(6, RebalancingVanilla.CONFIG.goldenCarrotSaturationModifier, false).build();

    @Final
    @Shadow
    public static FoodComponent APPLE = makeFood(4, 0.3f, true).build();

    @Final
    @Shadow
    public static FoodComponent BEETROOT = makeFood(1, 0.6f, true).build();

    @ModifyConstant(method = "createStew", constant = @Constant(floatValue = 0.6f))
    private static float changeSaturation(float saturationModifier) {
        return RebalancingVanilla.CONFIG.stewSaturationModifier;
    }

    @Unique
    private static FoodComponent.Builder makeFood(int hunger, float saturationModifier, boolean potentialSnack) {
        FoodComponent.Builder builder = new FoodComponent.Builder().hunger(hunger).saturationModifier(saturationModifier);
        if (potentialSnack && RebalancingVanilla.CONFIG.fasterEating) {
            return builder.snack();
        } else {
            return builder;
        }
    }
}
