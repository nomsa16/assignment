package com.eoh.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Entity
public class LineItem{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long quantity;
    private String description;
    private BigDecimal unitPrice;
    @ManyToOne()
    private Invoice invoice;

    public BigDecimal getLineItemTotal(){
        return new BigDecimal(quantity * unitPrice.doubleValue()).setScale(2, RoundingMode.HALF_UP);
    }
}
