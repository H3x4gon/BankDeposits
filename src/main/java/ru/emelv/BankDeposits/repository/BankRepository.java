package ru.emelv.BankDeposits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.emelv.BankDeposits.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}