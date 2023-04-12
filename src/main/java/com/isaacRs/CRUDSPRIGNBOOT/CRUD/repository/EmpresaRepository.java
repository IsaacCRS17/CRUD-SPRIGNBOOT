package com.isaacRs.CRUDSPRIGNBOOT.CRUD.repository;

import com.isaacRs.CRUDSPRIGNBOOT.CRUD.entity.EmpresaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {

    @Query(value = "SELECT e FROM EmpresaEntity e ")
    Optional<List<EmpresaEntity>> findAllEmpresas(Pageable pageable);
    @Query(value = "SELECT count(e) FROM EmpresaEntity e ")
    Integer countAllEmpresas();

    @Query(value = "SELECT count(e) FROM EmpresaEntity e " +
            "WHERE e.uniqueIdentifier = :id ")
    Optional<EmpresaEntity> findById(String id);
}
