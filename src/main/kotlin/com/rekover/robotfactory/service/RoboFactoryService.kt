package com.rekover.robotfactory.service

import com.rekover.robotfactory.dto.OrderDto
import com.rekover.robotfactory.entities.CodePartsEnum
import com.rekover.robotfactory.entities.RobotPart

interface RoboFactoryService {

    fun requestRobot(parts : Set<CodePartsEnum>) : OrderDto

    fun getPartsList(): HashMap<CodePartsEnum, RobotPart>

    fun getAvailability(code : CodePartsEnum) : Int

}