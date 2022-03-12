package com.starwars.resistancesocialnetwork.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RebelNotFoundException extends RuntimeException {
  @Builder
  public RebelNotFoundException(String message) {
    super(message);
  }
}
