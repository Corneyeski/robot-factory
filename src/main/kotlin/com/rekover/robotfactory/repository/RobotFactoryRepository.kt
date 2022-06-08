package com.rekover.robotfactory.repository

import com.rekover.robotfactory.dto.OrderDto
import com.rekover.robotfactory.entities.CodePartsEnum
import com.rekover.robotfactory.entities.RobotPart
import kotlin.collections.HashMap

object RobotFactoryRepository {

    private var orders = mutableMapOf<Int, Double>()
    lateinit var pieces: HashMap<CodePartsEnum, RobotPart>
    val piecesGroup = hashMapOf(
        Pair("face", listOf(CodePartsEnum.A, CodePartsEnum.B, CodePartsEnum.C)),
        Pair("arms", listOf(CodePartsEnum.D, CodePartsEnum.E)),
        Pair("legs", listOf(CodePartsEnum.F, CodePartsEnum.G, CodePartsEnum.H)),
        Pair("material", listOf(CodePartsEnum.I, CodePartsEnum.J))
    )

    fun createOrder(price : Double) : OrderDto {

        val received = (orders.maxBy {
            it.key
        }?.key ?: 0) + 1

        orders[received] = price

        return OrderDto(received, price)
    }

}