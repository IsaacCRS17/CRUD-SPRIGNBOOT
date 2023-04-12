package com.isaacRs.CRUDSPRIGNBOOT.CRUD.controller;

import com.isaacRs.CRUDSPRIGNBOOT.CRUD.dto.EmpresaDTO;
import com.isaacRs.CRUDSPRIGNBOOT.CRUD.service.EmpresaService;
import com.isaacRs.CRUDSPRIGNBOOT.GLOBAL.ApiResponse;
import com.isaacRs.CRUDSPRIGNBOOT.GLOBAL.Pagination;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ApiResponse<Pagination<EmpresaDTO>> list(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size){
        return this.empresaService.getAllEmpresas(page, size);
    }

    @GetMapping("{id}")
    public ApiResponse<EmpresaDTO> one(@PathVariable String id){
        return this.empresaService.getOneEmpresa(id);
    }

    @PostMapping
    public ApiResponse<EmpresaDTO> add(@Valid @RequestBody EmpresaDTO empresaDTO){
        return this.empresaService.addEmpresa(empresaDTO);
    }

    @PutMapping
    public ApiResponse<EmpresaDTO> update(@Valid @RequestBody EmpresaDTO empresaDTO){
        return this.empresaService.updateEmpresa(empresaDTO);
    }

    @DeleteMapping("{id}")
    public ApiResponse<EmpresaDTO> delete(@PathVariable String id){
        return this.empresaService.deleteEmpresa(id);
    }
}
