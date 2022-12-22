package com.training.videoteca.pojo.response;

import com.training.videoteca.entitity.SaleEntity;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.pojo.SalePojo.SaleDeletePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {
    SaleEntity saleEntity;

    SaleDeletePojo saleDeletePojo;
    List<SaleEntity> saleEntities;
    private ErrorResponse errorResponse;

    public SaleResponse(List<SaleEntity> saleEntities, ErrorResponse errorResponse) {
        this.saleEntities = saleEntities;
        this.errorResponse = errorResponse;
    }

    public SaleResponse(SaleEntity saleEntity, ErrorResponse errorResponse) {
        this.saleEntity = saleEntity;
        this.errorResponse = errorResponse;
    }

    public SaleResponse(SaleDeletePojo saleDeletePojo, ErrorResponse errorResponse) {
        this.saleDeletePojo = saleDeletePojo;
        this.errorResponse = errorResponse;
    }
}
