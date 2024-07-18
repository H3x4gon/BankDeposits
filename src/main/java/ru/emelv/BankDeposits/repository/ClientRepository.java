package ru.emelv.BankDeposits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.emelv.BankDeposits.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}