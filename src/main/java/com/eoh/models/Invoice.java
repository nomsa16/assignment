package com.eoh.models;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String client;
    private long vatRate;
    private Date invoiceDate;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LineItem> lineItemList;


    private BigDecimal getSubTotal() {
        return getTotal().subtract(getVat());
    }
    public BigDecimal getVat(){
        return getTotal().multiply(new BigDecimal(vatRate/100)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal(){
       return new BigDecimal(lineItemList.stream().mapToDouble(lineItem -> lineItem.getLineItemTotal().doubleValue()).sum()).setScale(2, RoundingMode.HALF_UP);
    }
}
