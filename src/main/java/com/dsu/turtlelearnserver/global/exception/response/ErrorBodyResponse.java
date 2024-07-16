package com.dsu.turtlelearnserver.global.exception.response;

import java.io.Serializable;

public record ErrorBodyResponse(
    ErrorResponse errorInfo,
    Serializable body
) {

}
