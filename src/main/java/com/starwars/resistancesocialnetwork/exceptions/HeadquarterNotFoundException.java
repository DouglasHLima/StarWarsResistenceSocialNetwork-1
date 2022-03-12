package com.starwars.resistancesocialnetwork.exceptions;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeadquarterNotFoundException extends RuntimeException {
  @Builder
  public HeadquarterNotFoundException(String message) {
    super(message);
  }
}
