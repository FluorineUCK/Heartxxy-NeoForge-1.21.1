package io.github.soccyuwu.pregxxy

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager

object Pregxxy {
    const val MODID = "pregxxy"
    @JvmField val LOGGER = LogManager.getLogger(MODID)
    @JvmStatic fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MODID, path)
}
