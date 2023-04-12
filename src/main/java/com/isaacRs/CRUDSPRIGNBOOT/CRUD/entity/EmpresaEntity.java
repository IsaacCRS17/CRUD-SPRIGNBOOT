package com.isaacRs.CRUDSPRIGNBOOT.CRUD.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isaacRs.CRUDSPRIGNBOOT.CRUD.dto.EmpresaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "empresa")
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa", unique = true, nullable = false)
    private int id;

    @NotBlank
    @Column(name = "uniqueIdentifier_empresa", length = 40)
    private String uniqueIdentifier;

    @NotBlank
    @Column(name = "name_empresa", length = 10)
    private String name;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate dateCreate;

    public EmpresaDTO getEmpresaDTO(){
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(this.uniqueIdentifier);
        empresaDTO.setName(this.name);
        empresaDTO.setDateCreate(this.dateCreate);
        return empresaDTO;
    }

    public void setEmpresaDTO(EmpresaDTO empresaDTO){
        this.uniqueIdentifier = empresaDTO.getId();
        this.name=empresaDTO.getName();
        this.dateCreate=empresaDTO.getDateCreate();
    }
}
