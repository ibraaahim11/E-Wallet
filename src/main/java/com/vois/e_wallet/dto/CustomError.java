package com.vois.e_wallet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomError {
  int status;
  String message;
}
