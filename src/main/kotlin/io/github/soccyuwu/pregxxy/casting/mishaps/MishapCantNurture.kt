package io.github.soccyuwu.pregxxy.casting.mishaps

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.Mishap
import at.petrak.hexcasting.api.pigment.FrozenPigment
import at.petrak.hexcasting.api.utils.TreeList
import at.petrak.hexcasting.api.utils.aqua
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.DyeColor

class MishapCantNurture(private val entity: Entity) : Mishap() {
    override fun accentColor(ctx: CastingEnvironment, errorCtx: Context): FrozenPigment = dyeColor(DyeColor.BROWN)
    override fun execute(env: CastingEnvironment, errorCtx: Context, stack: TreeList<Iota>) = stack
    override fun errorMessage(ctx: CastingEnvironment, errorCtx: Context) =
        error("cantNurture", entity.name.copy().aqua)
}
