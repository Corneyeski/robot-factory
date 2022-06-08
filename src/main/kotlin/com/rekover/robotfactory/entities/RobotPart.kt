package com.rekover.robotfactory.entities

data class RobotPart(
    var code: CodePartsEnum?,
    var price: Double,
    var available: Int,
    var name: String
    )
