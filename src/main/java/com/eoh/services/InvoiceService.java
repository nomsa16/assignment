package com.eoh.services;

import com.eoh.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eoh.repos.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository repository;

    public Invoice getInvoice(final long invoiceId){
        return repository.findOne(invoiceId);
    }

    public List<Invoice> getInvoices(){
        List<Invoice> target = new ArrayList<>();
        repository.findAll().forEach(target::add);
        return target;
    }

    public void addInvoice(final Invoice invoice){
        repository.save(invoice);
    }
}
