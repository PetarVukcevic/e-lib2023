package com.petarvukcevic.elib.dto.command;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoryCommand {

    @NotNull(message = "Name is required") // required
    @NotEmpty(message = "Name is empty") // ""
    @NotBlank(message = "Name is blank") // "      " blank
    @Size(min = 5, max = 20, message = "Invalid size")
//    @Min() | Max()
//    @Email
//    @Pattern(regexp = "")
    private String name;
    private String description;
}
