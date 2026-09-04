package io.github.soccyuwu.pregxxy.neo

import io.github.soccyuwu.pregxxy.Pregxxy
import io.github.soccyuwu.pregxxy.config.PregxxyConfig
import io.github.soccyuwu.pregxxy.networking.PregxxyNetworking
import io.github.soccyuwu.pregxxy.registry.PregxxyActions
import net.minecraft.server.level.ServerPlayer
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.entity.player.PlayerEvent

@Mod(Pregxxy.MODID)
class PregxxyNeo(modBus: IEventBus, container: ModContainer) {
    init {
        PregxxyActions.register(modBus)
        PregxxyNetworking.register(modBus)
        container.registerConfig(ModConfig.Type.CLIENT, PregxxyConfig.CLIENT_SPEC)
        container.registerConfig(ModConfig.Type.SERVER, PregxxyConfig.SERVER_SPEC)
        NeoForge.EVENT_BUS.addListener(::onLogin)
        Pregxxy.LOGGER.info("Heartxxy patterns registered for Hex Casting pre-39")
    }

    private fun onLogin(event: PlayerEvent.PlayerLoggedInEvent) {
        (event.entity as? ServerPlayer)?.let(PregxxyNetworking::sendConfig)
    }
}
