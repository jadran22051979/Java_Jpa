package com.example.Java_Jpa.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HardwareDTO {
    @NotBlank(message = "Hardware name cannot be blank")
    private String hardwareName;
    @NotBlank(message = "Hardware code cannot be blank")
    private String hardwareCode;
    @DecimalMin(value = "0.0", message = "Hardware price must be positive")
    private BigDecimal hardwarePrice;
    @NotNull
    private Integer hardwareQuantity;
    @NotBlank(message = "Category name cannot be blank")
    private String categoryName;
}
