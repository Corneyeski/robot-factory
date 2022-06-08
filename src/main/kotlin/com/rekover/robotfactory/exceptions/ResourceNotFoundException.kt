package com.rekover.robotfactory.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Part requested not found")
class ResourceNotFoundException : Exception() {
}