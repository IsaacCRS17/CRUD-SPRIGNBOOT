package com.isaacRs.CRUDSPRIGNBOOT.CRUD.service;

import com.isaacRs.CRUDSPRIGNBOOT.CRUD.dto.EmpresaDTO;
import com.isaacRs.CRUDSPRIGNBOOT.CRUD.entity.EmpresaEntity;
import com.isaacRs.CRUDSPRIGNBOOT.CRUD.repository.EmpresaRepository;
import com.isaacRs.CRUDSPRIGNBOOT.GLOBAL.ApiResponse;
import com.isaacRs.CRUDSPRIGNBOOT.GLOBAL.Pagination;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public ApiResponse<Pagination<EmpresaDTO>> getAllEmpresas(int page, int size) {
        log.info("page size {} {}", page, size);
        ApiResponse<Pagination<EmpresaDTO>> apiResponse = new ApiResponse<>();
        Pagination<EmpresaDTO> pagination = new Pagination<>();
        pagination.setCountFilter(this.empresaRepository.countAllEmpresas());
        if (pagination.getCountFilter() > 0) {
            Pageable pageable = PageRequest.of(page, size);
            List<EmpresaEntity> empresaEntities = this.empresaRepository.findAllEmpresas(pageable).orElse(new ArrayList<>());
            pagination.setList(empresaEntities.stream().map(EmpresaEntity::getEmpresaDTO).collect(Collectors.toList()));
        }
        pagination.setTotalPages(pagination.processAndGetTotalPages(size));
        apiResponse.setData(pagination);
        apiResponse.setSuccessful(true);
        apiResponse.setMessage("Lista de Empresas");
        return apiResponse;
    }


    public ApiResponse<EmpresaDTO> getOneEmpresa(String id) {
        log.info("id {}", id);
        ApiResponse<EmpresaDTO> apiResponse = new ApiResponse<>();
        EmpresaEntity empresaEntity = empresaRepository.findById(id).get();
        EmpresaDTO empresaDTO = empresaEntity.getEmpresaDTO();
        apiResponse.setData(empresaDTO);
        apiResponse.setSuccessful(true);
        apiResponse.setMessage(empresaDTO.getName());
        return apiResponse;
    }

    public ApiResponse<EmpresaDTO> addEmpresa(EmpresaDTO empresaDTO) {
        ApiResponse<EmpresaDTO> apiResponse = new ApiResponse<>();
        empresaDTO.setId(UUID.randomUUID().toString());
        empresaDTO.setDateCreate(LocalDate.now());

        EmpresaEntity empresaEntity = new EmpresaEntity();
        empresaEntity.setEmpresaDTO(empresaDTO);
        empresaEntity.setName(empresaDTO.getName().toUpperCase());

        apiResponse.setData(this.empresaRepository.save(empresaEntity).getEmpresaDTO());
        apiResponse.setSuccessful(true);
        apiResponse.setMessage("Empresa creada");
        return apiResponse;
    }

    public ApiResponse<EmpresaDTO> updateEmpresa(EmpresaDTO empresaDTO) {
        ApiResponse<EmpresaDTO> apiResponse = new ApiResponse<>();
        EmpresaEntity empresaEntity = this.empresaRepository.findById(empresaDTO.getId()).get();
        empresaEntity.setName(empresaDTO.getName().toUpperCase());

        apiResponse.setData(this.empresaRepository.save(empresaEntity).getEmpresaDTO());
        apiResponse.setSuccessful(true);
        apiResponse.setMessage("Empresa actualizada");
        return apiResponse;
    }

    public ApiResponse<EmpresaDTO> deleteEmpresa(String id){
        ApiResponse<EmpresaDTO> apiResponse = new ApiResponse<>();
        EmpresaEntity empresaEntity = this.empresaRepository.findById(id).get();
        this.empresaRepository.delete(empresaEntity);
        apiResponse.setData(empresaEntity.getEmpresaDTO());
        apiResponse.setSuccessful(true);
        apiResponse.setMessage("Empresa eliminada");
        return apiResponse;
    }
}