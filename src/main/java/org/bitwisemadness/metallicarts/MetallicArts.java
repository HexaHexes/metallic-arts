package org.bitwisemadness.metallicarts;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

/**
 * The Metallic Arts mod entry point.
 *
 * <p>Planning phase: the gameplay design lives in {@code docs/} - the brain
 * dump, design, integrations and roadmap documents. Code lands milestone by
 * milestone from that plan; the first spike is a single metal burned end to
 * end through Ability Core and Sky's Ley Lines.</p>
 */
@Mod(MetallicArts.MOD_ID)
public final class MetallicArts {

    public static final String MOD_ID = "metallic_arts";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MetallicArts(IEventBus modBus) {
        LOGGER.info("The Metallic Arts initialized");
    }
}
