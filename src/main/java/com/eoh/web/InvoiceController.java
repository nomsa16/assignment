package com.eoh.web;

import com.eoh.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.eoh.services.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
        private InvoiceService service;

        @Autowired
        public InvoiceController(final InvoiceService service) {
            this.service = service;
        }

        @PostMapping("")
        public ResponseEntity addInvoice(final @RequestBody Invoice invoice) {
            service.addInvoice(invoice);
            return new ResponseEntity("Invoice added", HttpStatus.OK);
        }

        @GetMapping(value = "/{invoiceId}")
        public ResponseEntity getInvoiceById(@PathVariable("invoiceId") final long id) {
            return new ResponseEntity(service.getInvoice(id), HttpStatus.OK);
        }

        @GetMapping("")
        public ResponseEntity getAllInvoices(){
            return new ResponseEntity(service.getInvoices(), HttpStatus.OK);
        }
    }

