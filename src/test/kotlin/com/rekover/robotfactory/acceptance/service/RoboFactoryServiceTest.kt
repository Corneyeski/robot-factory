package com.rekover.robotfactory.acceptance.service

import com.rekover.robotfactory.entities.CodePartsEnum
import com.rekover.robotfactory.exceptions.BadRequestException
import com.rekover.robotfactory.exceptions.OutOfStockException
import com.rekover.robotfactory.repository.RobotFactoryRepository
import com.rekover.robotfactory.service.RoboFactoryServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
    class RoboFactoryServiceTest {

    @Autowired
    private lateinit var roboFactoryService: RoboFactoryServiceImpl


    @BeforeEach
    fun before(){
        RobotFactoryRepository.pieces[CodePartsEnum.F]!!.available = 2
    }

    @Test
    fun requestRobot_OK(){
        val result = roboFactoryService.requestRobot(setOf( CodePartsEnum.I, CodePartsEnum.A, CodePartsEnum.D, CodePartsEnum.F))

        assertEquals(result.orderId, 2)
    }

    @Test
    fun requestRobot_BAD_REQUEST(){
        assertThrows<BadRequestException> {
            roboFactoryService.requestRobot(setOf( CodePartsEnum.I, CodePartsEnum.A, CodePartsEnum.D, CodePartsEnum.D))
        }
    }

    @Test
    fun requestRobot_OUT_OF_STOCK(){
        RobotFactoryRepository.pieces[CodePartsEnum.F]!!.available = 0

        assertThrows<OutOfStockException> {
            roboFactoryService.requestRobot(setOf( CodePartsEnum.I, CodePartsEnum.A, CodePartsEnum.D, CodePartsEnum.F))
        }

        RobotFactoryRepository.pieces[CodePartsEnum.F]!!.available = 0
    }

    @Test
    fun getPartsList_OK(){
        val parts = roboFactoryService.getPartsList()
        assertEquals(parts.size, 10)
    }

    @Test
    fun getAvailability_OK(){
        val available = roboFactoryService.getAvailability(CodePartsEnum.A)
        assertEquals(available, 9)
    }

}