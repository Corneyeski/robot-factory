package com.rekover.robotfactory.service

import com.rekover.robotfactory.dto.OrderDto
import com.rekover.robotfactory.entities.CodePartsEnum
import com.rekover.robotfactory.repository.RobotFactoryRepository
import com.rekover.robotfactory.entities.RobotPart
import com.rekover.robotfactory.exceptions.BadRequestException
import com.rekover.robotfactory.exceptions.OutOfStockException
import com.rekover.robotfactory.exceptions.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class RoboFactoryServiceImpl : RoboFactoryService {

    override fun requestRobot(parts: Set<CodePartsEnum>): OrderDto {

        var partsResults = validateParts(parts)

        partsResults.forEach {
            it.available--
            RobotFactoryRepository.pieces[it.code]!!.available = it.available
        }

        return RobotFactoryRepository.createOrder(partsResults.sumByDouble{it.price})
    }

    override fun getPartsList(): HashMap<CodePartsEnum, RobotPart> {
        return RobotFactoryRepository.pieces
    }

    override fun getAvailability(code: CodePartsEnum): Int {
        return RobotFactoryRepository.pieces[code]?.available ?: throw ResourceNotFoundException()
    }

    private fun validateParts(parts: Set<CodePartsEnum>): List<RobotPart> {

        if (parts.size != 4)
            throw BadRequestException()

        return RobotFactoryRepository.piecesGroup.map { entry ->
            RobotFactoryRepository.pieces[entry.value.filter { parts.contains(it) }
                .ifEmpty {
                    throw ResourceNotFoundException()
                }.first()]!!.takeIf {
                it.available > 0
                } ?: throw OutOfStockException()
        }
    }
}