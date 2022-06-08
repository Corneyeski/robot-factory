package com.rekover.robotfactory.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Out of stock in some materials")
class OutOfStockException : Exception()