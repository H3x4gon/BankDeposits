package ru.emelv.BankDeposits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.emelv.BankDeposits.entity.Deposit;
import ru.emelv.BankDeposits.repository.DepositRepository;
import ru.emelv.BankDeposits.service.DepositService;

public class DepositServiceTest {

    @Mock
    private DepositRepository depositRepository;

    @InjectMocks
    private DepositService depositService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDeposits() {
        Deposit deposit1 = new Deposit();
        deposit1.setId(1L);
        deposit1.setAmount(new BigDecimal("1000.00"));

        Deposit deposit2 = new Deposit();
        deposit2.setId(2L);
        deposit2.setAmount(new BigDecimal("2000.00"));

        List<Deposit> deposits = Arrays.asList(deposit1, deposit2);

        when(depositRepository.findAll()).thenReturn(deposits);

        List<Deposit> result = depositService.getAllDeposits();
        assertEquals(2, result.size());
        verify(depositRepository, times(1)).findAll();
    }

    @Test
    void testGetDepositById() {
        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setAmount(new BigDecimal("1000.00"));

        when(depositRepository.findById(anyLong())).thenReturn(Optional.of(deposit));

        Optional<Deposit> result = depositService.getDepositById(1L);
        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("1000.00"), result.get().getAmount());
        verify(depositRepository, times(1)).findById(anyLong());
    }

    @Test
    void testCreateDeposit() {
        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setAmount(new BigDecimal("1000.00"));

        when(depositRepository.save(any(Deposit.class))).thenReturn(deposit);

        Deposit result = depositService.createDeposit(deposit);
        assertEquals(new BigDecimal("1000.00"), result.getAmount());
        verify(depositRepository, times(1)).save(any(Deposit.class));
    }

    @Test
    void testUpdateDeposit() {
        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setAmount(new BigDecimal("1000.00"));

        when(depositRepository.findById(anyLong())).thenReturn(Optional.of(deposit));
        when(depositRepository.save(any(Deposit.class))).thenReturn(deposit);

        Deposit updatedDeposit = new Deposit();
        updatedDeposit.setAmount(new BigDecimal("2000.00"));

        Deposit result = depositService.updateDeposit(1L, updatedDeposit);
        assertEquals(new BigDecimal("2000.00"), result.getAmount());
        verify(depositRepository, times(1)).findById(anyLong());
        verify(depositRepository, times(1)).save(any(Deposit.class));
    }

    @Test
    void testDeleteDeposit() {
        doNothing().when(depositRepository).deleteById(anyLong());

        depositService.deleteDeposit(1L);
        verify(depositRepository, times(1)).deleteById(anyLong());
    }
}