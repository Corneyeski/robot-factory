package com.rekover.robotfactory.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class OrderDto (
    @JsonProperty("order_id")
    var orderId : Int,
    var total : Double
)