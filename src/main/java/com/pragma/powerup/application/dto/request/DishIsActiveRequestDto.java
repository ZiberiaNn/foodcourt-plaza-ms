package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishIsActiveRequestDto {
    @Schema(example = "false")
    private Boolean isActive;
}
