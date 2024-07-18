package ru.emelv.BankDeposits.controller;

import ru.emelv.BankDeposits.entity.Bank;
import ru.emelv.BankDeposits.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public List<Bank> getAllBanks(@RequestParam(required = false) String sortBy) {
        Sort sort = Sort.by(sortBy != null ? sortBy : "id");
        return bankService.getAllBanks(sort);
    }

    @GetMapping("/{id}")
    public Optional<Bank> getBankById(@PathVariable Long id) {
        return bankService.getBankById(id);
    }

    @PostMapping
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.createBank(bank);
    }

    @PutMapping("/{id}")
    public Bank updateBank(@PathVariable Long id, @RequestBody Bank updatedBank) {
        return bankService.updateBank(id, updatedBank);
    }

    @DeleteMapping("/{id}")
    public void deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
    }
}