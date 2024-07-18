package ru.emelv.BankDeposits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.emelv.BankDeposits.entity.Deposit;
import ru.emelv.BankDeposits.service.DepositService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deposits")
public class DepositController {

    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping
    public List<Deposit> getAllDeposits() {
        return depositService.getAllDeposits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable Long id) {
        Optional<Deposit> deposit = depositService.getDepositById(id);
        return deposit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Deposit createDeposit(@RequestBody Deposit deposit) {
        return depositService.createDeposit(deposit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deposit> updateDeposit(@PathVariable Long id, @RequestBody Deposit depositDetails) {
        try {
            Deposit updatedDeposit = depositService.updateDeposit(id, depositDetails);
            return ResponseEntity.ok(updatedDeposit);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long id) {
        depositService.deleteDeposit(id);
        return ResponseEntity.noContent().build();
    }
}