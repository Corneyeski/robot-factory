package com.rekover.robotfactory.utils

import com.rekover.robotfactory.entities.CodePartsEnum
import com.rekover.robotfactory.repository.RobotFactoryRepository
import com.rekover.robotfactory.entities.RobotPart
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DataBuild {

    @PostConstruct
    fun buildInitialData() {
        RobotFactoryRepository.pieces = hashMapOf(
            Pair(CodePartsEnum.A, RobotPart(CodePartsEnum.A, 10.28, 9, "Humanoid Face")),
            Pair(CodePartsEnum.B, RobotPart(CodePartsEnum.B, 24.07, 7, "LCD Face")),
            Pair(CodePartsEnum.C, RobotPart(CodePartsEnum.C, 13.30, 0, "Steampunk Face")),
            Pair(CodePartsEnum.D, RobotPart(CodePartsEnum.D, 28.94, 1, "Arms with Hands")),
            Pair(CodePartsEnum.E, RobotPart(CodePartsEnum.E, 12.39, 3, "rms with Grippers")),
            Pair(CodePartsEnum.F, RobotPart(CodePartsEnum.F, 30.77, 2, "Mobility with Wheels")),
            Pair(CodePartsEnum.G, RobotPart(CodePartsEnum.G, 55.13, 15, "Mobility with Legs")),
            Pair(CodePartsEnum.H, RobotPart(CodePartsEnum.H, 50.00, 7, "Mobility with Tracks")),
            Pair(CodePartsEnum.I, RobotPart(CodePartsEnum.I, 90.12, 92, "Material Bioplastic")),
            Pair(CodePartsEnum.J, RobotPart(CodePartsEnum.J, 82.31, 15, "Material Metallic"))
        )
    }
}