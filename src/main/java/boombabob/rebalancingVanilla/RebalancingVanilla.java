package boombabob.rebalancingVanilla;

import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RebalancingVanilla implements ModInitializer {
	public static final String MODID = "rebalancing-vanilla";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final RebalancingVanillaConfig CONFIG = OmegaConfig.register(RebalancingVanillaConfig.class);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}