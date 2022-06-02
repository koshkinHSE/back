package com.twowasik_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Unauthorized")
public class InvalidTokenExceptions extends RuntimeException { }