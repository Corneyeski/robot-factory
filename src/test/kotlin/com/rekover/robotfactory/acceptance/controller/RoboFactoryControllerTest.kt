package com.rekover.robotfactory.acceptance.controller

import com.jayway.jsonpath.TypeRef
import com.rekover.robotfactory.controller.RoboFactoryController
import com.rekover.robotfactory.entities.CodePartsEnum
import com.rekover.robotfactory.entities.RobotPart
import com.rekover.robotfactory.repository.RobotFactoryRepository
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoboFactoryControllerTest {

    @LocalServerPort
    val springBootPort: Int = 0

    @BeforeEach
    fun before(){
        RobotFactoryRepository.pieces[CodePartsEnum.A]!!.available = 9
    }

    @Test
    fun listParts(){
       val test : Map<CodePartsEnum, RobotPart> =  get()
           .jsonPath().getMap(".")

        assertEquals(test.size, 10)
    }

    @Test
    fun getAvailability_OK(){
        getWithVariable("A")
            .then().statusCode(HttpStatus.OK.value())
            .assertThat().body(equalTo("9"))
    }

    private fun get() = RestAssured
        .given()
        .contentType(ContentType.JSON)
        .`when`()
        .port(springBootPort)
        .get("/orders")

    private fun getWithVariable(value : String) = RestAssured
        .given()
        .contentType(ContentType.JSON)
        .`when`()
        .port(springBootPort)
        .get("/orders/$value")
}