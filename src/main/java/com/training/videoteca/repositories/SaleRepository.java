package com.training.videoteca.repositories;

import com.training.videoteca.entitity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity,Long > {

    SaleEntity save (SaleEntity s);
    void deleteById (Long id);
    SaleEntity findByNomeAndDescrizione (int nome, String descrizione);
    SaleEntity findSaleById(Long id);

    List<SaleEntity> findAll();

}
