package com.training.videoteca.services.implementation;

import com.training.videoteca.entitity.SaleEntity;
import com.training.videoteca.repositories.SaleRepository;
import com.training.videoteca.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    @Transactional
    public SaleEntity save(SaleEntity s) {
        return saleRepository.save(s);
    }

    @Override
    public SaleEntity findSaleById(Long id) {
        return saleRepository.findSaleById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
     saleRepository.deleteById(id);
    }

    @Override
    public List<SaleEntity> findAll() {
        return saleRepository.findAll();
    }
}
