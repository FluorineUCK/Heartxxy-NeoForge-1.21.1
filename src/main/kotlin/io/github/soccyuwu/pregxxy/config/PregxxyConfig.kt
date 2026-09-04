package io.github.soccyuwu.pregxxy.config

import net.neoforged.neoforge.common.ModConfigSpec

object PregxxyConfig {
    private val clientBuilder = ModConfigSpec.Builder()
    @JvmField val CLIENT_CONFIG_OPTION: ModConfigSpec.BooleanValue = clientBuilder
        .comment("Placeholder client option retained from the original AutoConfig schema.")
        .define("client_config_option", true)
    @JvmField val CLIENT_SPEC: ModConfigSpec = clientBuilder.build()

    private val serverBuilder = ModConfigSpec.Builder()
    @JvmField val SERVER_CONFIG_OPTION: ModConfigSpec.IntValue = serverBuilder
        .comment("Placeholder server option retained and synchronized to clients.")
        .defineInRange("server_config_option", 64, Int.MIN_VALUE, Int.MAX_VALUE)
    @JvmField val SERVER_SPEC: ModConfigSpec = serverBuilder.build()

    @Volatile private var syncedServerConfig: Int? = null

    val clientConfigOption: Boolean get() = CLIENT_CONFIG_OPTION.get()
    val serverConfigOption: Int get() = syncedServerConfig ?: SERVER_CONFIG_OPTION.get()

    fun acceptServerSync(value: Int) {
        syncedServerConfig = value
    }

    fun clearServerSync() {
        syncedServerConfig = null
    }
}
