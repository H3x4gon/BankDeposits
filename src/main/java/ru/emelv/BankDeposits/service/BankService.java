package ru.emelv.BankDeposits.service;

import ru.emelv.BankDeposits.entity.Bank;
import ru.emelv.BankDeposits.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public List<Bank> getAllBanks(Sort sort) {
        return bankRepository.findAll(sort);
    }

    public Optional<Bank> getBankById(Long id) {
        return bankRepository.findById(id);
    }

    public Bank createBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank updateBank(Long id, Bank updatedBank) {
        return bankRepository.findById(id).map(bank -> {
            bank.setName(updatedBank.getName());
            bank.setAddress(updatedBank.getAddress());
            return bankRepository.save(bank);
        }).orElseThrow(() -> new RuntimeException("Bank not found"));
    }

    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }
}