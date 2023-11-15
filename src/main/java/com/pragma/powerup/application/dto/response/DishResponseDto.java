package com.pragma.powerup.application.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DishResponseDto {
    @Schema(example = "0")
    private Long id;
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
    @Schema(example = "Example Category")
    private String category;
    private RestaurantSavedResponseDto restaurant;
}
