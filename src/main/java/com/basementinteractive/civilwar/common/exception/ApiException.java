package com.basementinteractive.civilwar.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
@AllArgsConstructor
public class ApiException {

    private final String message;

    private final Integer httpStatus;

    private final ZonedDateTime timestamp;

}
