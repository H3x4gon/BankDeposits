package ru.emelv.BankDeposits.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emelv.BankDeposits.entity.Deposit;
import ru.emelv.BankDeposits.repository.DepositRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    private final DepositRepository depositRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public List<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }

    public Optional<Deposit> getDepositById(Long id) {
        return depositRepository.findById(id);
    }

    public Deposit createDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    public Deposit updateDeposit(Long id, Deposit depositDetails) {
        Optional<Deposit> optionalDeposit = depositRepository.findById(id);

        if (optionalDeposit.isPresent()) {
            Deposit deposit = optionalDeposit.get();
            deposit.setAmount(depositDetails.getAmount());
            deposit.setInterestRate(depositDetails.getInterestRate());
            deposit.setStartDate(depositDetails.getStartDate());
            deposit.setEndDate(depositDetails.getEndDate());
            deposit.setClientId(depositDetails.getClientId());
            deposit.setBankId(depositDetails.getBankId());
            return depositRepository.save(deposit);
        } else {
            throw new RuntimeException("Deposit not found with id " + id);
        }
    }

    public void deleteDeposit(Long id) {
        depositRepository.deleteById(id);
    }
}