package com.rekover.robotfactory.acceptance

import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderARobotAcceptanceTest {

    @LocalServerPort
    val springBootPort: Int = 0

    @Test
    fun `should order a robot`() {
        postOrder(
            """
                    ["I","A","D","F"]
                    
                """
        ).then()
            .assertThat()
            .statusCode(HttpStatus.CREATED.value())
            .body("order_id", notNullValue())
            .body("total", equalTo(160.11f))
    }

    @Test
    fun `should not allow invalid body`() {
        postOrder(
            """
                   "BENDER"
                  
                """
        ).then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value())
    }

    @Test
    fun `should not allow invalid robot configuration`() {
        postOrder(
            """
                     ["A", "C", "I", "D"]
                    
                """
        ).then()
            .assertThat()
            .statusCode(HttpStatus.CONFLICT.value())
    }

    private fun postOrder(body: String) = RestAssured
        .given()
        .body(body)
        .contentType(ContentType.JSON)
        .`when`()
        .port(springBootPort)
        .post("/orders")
}
