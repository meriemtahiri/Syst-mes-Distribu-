package com.tahiri.bilingservice.WEB;

import com.tahiri.bilingservice.ENTITIES.Bill;
import com.tahiri.bilingservice.REPOSITORIES.BillRepo;
import com.tahiri.bilingservice.SERVICES.CustomerRestClient;
import com.tahiri.bilingservice.SERVICES.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillRestController {

    private BillRepo billRepo;
    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;
    public BillRestController(BillRepo billRepo, CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
        this.billRepo = billRepo;
        this.customerRestClient = customerRestClient;
        this.productRestClient=productRestClient;
    }

    // Endpoint pour récupérer toutes les factures
    @GetMapping
    public List<Bill> getAllBills() {
        List<Bill> bills = billRepo.findAll();
        bills.forEach(bill -> {
            bill.setCustomer(customerRestClient.getCustomerById(bill.getCostumerId()));
            bill.getProductItems().forEach(productItem -> {
                productItem.setProduct(productRestClient.getProductById(productItem.getId()));
            });
        });
        return bills;
    }

    // Endpoint pour récupérer une facture par ID
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        return billRepo.findById(id)
                .map(bill -> ResponseEntity.ok().body(bill))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint pour créer une nouvelle facture
    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billRepo.save(bill);
    }

    // Endpoint pour mettre à jour une facture existante
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill billDetails) {
        return billRepo.findById(id)
                .map(bill -> {
                    bill.setCostumerId(billDetails.getCostumerId());
                    bill.setCustomer(billDetails.getCustomer());
                    bill.setStatus(billDetails.getStatus());
                    bill.setCreatedAt(billDetails.getCreatedAt());
                    bill.setProductItems(billDetails.getProductItems());
                    Bill updatedBill = billRepo.save(bill);
                    return ResponseEntity.ok().body(updatedBill);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint pour supprimer une facture par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBill(@PathVariable Long id) {
        return billRepo.findById(id)
                .map(bill -> {
                    billRepo.delete(bill);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

