package com.arilsondev.publictaxmanagement.resources;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController {

  long ACCEPT_RANGE = 50;

  default <T> ResponseEntity<List<T>> partialContent(Page<T> page, long acceptRange) {
    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
      .header("content-range", String.valueOf(page.getTotalElements()))
      .header("content-pages", String.valueOf(page.getTotalPages()))
      .header("accept-range", String.valueOf(acceptRange))
      .body(page.getContent());
  }
}
