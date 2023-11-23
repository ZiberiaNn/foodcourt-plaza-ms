package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequestDto {
    @Schema(example = "Example Name")
    private String name;
    @Schema(example = "9500")
    private Integer price;
    @Schema(example = "Example Description")
    private String description;
    @Schema(example = "www.example.com/image.png")
    private String urlImage;
    @Schema(example = "true")
    private Boolean isActive;
    private Long categoryId;
    @Schema(example = "1")
    private Long restaurantId;
}
