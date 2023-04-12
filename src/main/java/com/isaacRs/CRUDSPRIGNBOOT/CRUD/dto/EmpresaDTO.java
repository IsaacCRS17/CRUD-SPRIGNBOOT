package com.isaacRs.CRUDSPRIGNBOOT.CRUD.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class EmpresaDTO {

    private String id;

    @NotBlank(message = "name cannot be null")
    private String name;

    private LocalDate dateCreate;
}
