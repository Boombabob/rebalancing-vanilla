package boombabob.rebalancingVanilla;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import org.jetbrains.annotations.Nullable;

public class RebalancingVanillaConfig implements Config {

    @Override
    public String getName() {
        return "rebalancing_vanilla_config";
    }

    @Override
    public @Nullable String getModid() {
        return RebalancingVanilla.MODID;
    }

    @Comment("""
        For number values in this config, there are several suggested values listed:
        - Vanilla, which is the value to make it the same as vanilla minecraft
        - Default, which is the default value for the mod
        - Suggested max/min, which is the min and max value for it to be stable and work as intended
        
        Horse speed multiplier
        Vanilla: 1.0
        Default: 1.5
        Suggested max: 5.0
            """)
    public float horseSpeedMultiplier = 1.5f;

    @Comment("""
        Horse speed multiplier when on a dirt path (does not stack with horseSpeedMultiplier)
        Vanilla: 1.0
        Default: 2.0
        Suggested min/max: 0.95, 1.002
            """)
    public float horseDirtPathSpeedMultiplier = 2.0f;

    @Comment("""
        Whether horses swim when you are riding them
            """)
    public boolean horsesSwim = true;

    @Comment("""
        Furnace minecart speed multiplier
        Vanilla: 1.0
        Default: 1.5
        Suggested max: 3.0
            """)
    public float furnaceMinecartSpeedMultiplier = 1.5f;

    @Comment("""
        Firework rocket flight time divisor
        Set to 0 to disable firework rocket boosting
        Vanilla: 1.0
        Default: 3.0
        Suggested max: 7.0
            """)
    public float fireworkFlightTimeDivisor = 3.0f;

    @Comment("""
        Firework rocket boost strength multiplier
        Set to 0 to disable firework rocket boosting
        Vanilla: 1.5
        Default: 0.5
            """)
    public float fireworkBoostStrengthMultiplier = 0.5f;

    @Comment("""
        Whether firework levels should increase flight duration or boost amount
        """)
    public boolean fireworkLevelChangesBoostNotDuration = true;

    @Comment("""
        Elytra speed multiplier (very sensitive! anything above ~1.002 allows for infinite flight & speed)
        Vanilla: 1.0
        Default: 0.985
        Suggested min/max: 0.95, 1.002
            """)
    public float elytraSpeedMultiplier = 0.985f;

    @Comment("""
        Elytra speed multiplier (very sensitive! anything above ~1.002 allows for infinite flight & speed)
        Vanilla: 1.0
        Default: 0.985
        Suggested min/max: 0.95, 1.002
            """)
    public float elytraSpeedMultiplier = 0.985f;
}
