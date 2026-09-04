package io.github.soccyuwu.pregxxy.client

import io.github.soccyuwu.pregxxy.Pregxxy
import io.github.soccyuwu.pregxxy.config.PregxxyConfig
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent

@EventBusSubscriber(modid = Pregxxy.MODID, value = [Dist.CLIENT])
object PregxxyClientEvents {
    @JvmStatic
    @SubscribeEvent
    fun onLogout(event: ClientPlayerNetworkEvent.LoggingOut) {
        PregxxyConfig.clearServerSync()
    }
}
