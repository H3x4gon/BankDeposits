package ru.emelv.BankDeposits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.emelv.BankDeposits.entity.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
}