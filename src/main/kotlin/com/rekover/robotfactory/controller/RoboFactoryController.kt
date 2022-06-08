package com.rekover.robotfactory.controller

import com.rekover.robotfactory.dto.OrderDto
import com.rekover.robotfactory.entities.CodePartsEnum
import com.rekover.robotfactory.entities.RobotPart
import com.rekover.robotfactory.service.RoboFactoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
 class RoboFactoryController(
   @Autowired
   private var roboFactoryService: RoboFactoryService
) {

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    fun createRobot(@RequestBody parts : Set<CodePartsEnum>) : OrderDto {
        return roboFactoryService.requestRobot(parts)
    }

    @GetMapping
    fun listParts() : HashMap<CodePartsEnum, RobotPart> {
       return roboFactoryService.getPartsList()
    }

    @GetMapping("/{code}")
    fun checkAvailability(@PathVariable code: CodePartsEnum) : Int {
       return roboFactoryService.getAvailability(code)
    }
}