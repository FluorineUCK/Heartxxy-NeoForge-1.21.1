package io.github.soccyuwu.pregxxy.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexRegistries
import io.github.soccyuwu.pregxxy.Pregxxy
import io.github.soccyuwu.pregxxy.casting.actions.spells.OpBreed
import io.github.soccyuwu.pregxxy.casting.actions.spells.OpGetAge
import io.github.soccyuwu.pregxxy.casting.actions.spells.OpNurture
import io.github.soccyuwu.pregxxy.casting.actions.spells.great.OpGreaterBreed
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object PregxxyActions {
    private val ACTIONS = DeferredRegister.create(HexRegistries.ACTION, Pregxxy.MODID)

    init {
        register("pregxxy", HexDir.NORTH_EAST, "eewewwwwdwwwweweewwdeaqq", OpBreed)
        register("greater_pregxxy", HexDir.NORTH_EAST,
            "wewewewwwwwewewwwwwewewewqadewwdwweweaqwwaeeawww", OpGreaterBreed)
        register("nurture", HexDir.SOUTH_EAST, "deaqwaw", OpNurture)
        register("get_age", HexDir.SOUTH_EAST, "deaqqwa", OpGetAge)
    }

    fun register(bus: IEventBus) = ACTIONS.register(bus)

    private fun register(name: String, start: HexDir, signature: String, action: Action) {
        ACTIONS.register(name, Supplier { ActionRegistryEntry(HexPattern.fromAngles(signature, start), action) })
    }
}
