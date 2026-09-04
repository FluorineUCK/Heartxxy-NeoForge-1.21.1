package io.github.soccyuwu.pregxxy.networking

import io.github.soccyuwu.pregxxy.Pregxxy
import io.github.soccyuwu.pregxxy.config.PregxxyConfig
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.server.level.ServerPlayer
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.network.PacketDistributor
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import net.neoforged.neoforge.network.handling.IPayloadContext

data class SyncConfigPayload(val serverConfigOption: Int) : CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

    companion object {
        val TYPE = CustomPacketPayload.Type<SyncConfigPayload>(Pregxxy.id("sync_config"))
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, SyncConfigPayload> = StreamCodec.of(
            { buffer, payload -> buffer.writeInt(payload.serverConfigOption) },
            { buffer -> SyncConfigPayload(buffer.readInt()) }
        )
    }
}

object PregxxyNetworking {
    fun register(bus: IEventBus) = bus.addListener(::registerPayloads)

    private fun registerPayloads(event: RegisterPayloadHandlersEvent) {
        event.registrar("1").playToClient(
            SyncConfigPayload.TYPE,
            SyncConfigPayload.STREAM_CODEC,
            ::handleConfigSync
        )
    }

    fun sendConfig(player: ServerPlayer) {
        PacketDistributor.sendToPlayer(player, SyncConfigPayload(PregxxyConfig.SERVER_CONFIG_OPTION.get()))
    }

    private fun handleConfigSync(payload: SyncConfigPayload, context: IPayloadContext) {
        context.enqueueWork { PregxxyConfig.acceptServerSync(payload.serverConfigOption) }
    }
}
