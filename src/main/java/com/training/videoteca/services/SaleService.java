package com.training.videoteca.services;


import com.training.videoteca.entitity.SaleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleService {
    SaleEntity save (SaleEntity s);
    SaleEntity findSaleById(Long id);

    void deleteById (Long id);
    List<SaleEntity> findAll();
}
