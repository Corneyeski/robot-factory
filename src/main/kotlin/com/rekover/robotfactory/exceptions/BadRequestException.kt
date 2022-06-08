package com.rekover.robotfactory.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested the incorrect parts")
class BadRequestException : Exception()