//package com.iagomesquita.financialControl.Transactions;
//
//import com.iagomesquita.financialControl.model.entity.Transaction;
//import com.iagomesquita.financialControl.service.TransactionService;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static com.iagomesquita.financialControl.model.enums.Type.INCOME;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TransactionTests {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @MockBean
//  private TransactionService transactionService;
//
//  @Test
//  @DisplayName("Rota GET para /transaction: Retorna todas as transações")
//  void ReturnStatusOK() throws Exception {
//    mockMvc.perform(get("/transactions"))
//        .andExpect(status().isOk());
//  }
//
//  @Test
//  @DisplayName("Quando a transacao nao e encontrada")
//  void TransactionNotFoundTest() throws Exception {
//
//    Mockito
//        .when(transactionService.getAllTransactions())
//        .thenReturn(Optional.empty());
//
//    ResultActions result = mockMvc.perform(get("/transactions"));
//
//    result.andExpect(status().isOk());
//
//    Mockito.verify(transactionService).getAllTransactions();
//
//  }
//
//}

// mockMvc.perform(get("/"))
//    .andExpect(status().isOk())
//    .andExpect(jsonPath("$.estado").value("Pernambuco"))
//    .andExpect(jsonPath("$.cidade").value("Recife")); // 3
//    }

package com.iagomesquita.financialControl.Transactions;

import com.iagomesquita.financialControl.model.entity.Transaction;
import com.iagomesquita.financialControl.model.enums.Type;
import com.iagomesquita.financialControl.model.repository.TransactionRepository;
import com.iagomesquita.financialControl.service.Exception.TransactionNotFount;
import com.iagomesquita.financialControl.service.TransactionService;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

  @Mock
  private TransactionRepository transactionRepository;

  @InjectMocks
  private TransactionService transactionService;

  private Transaction transaction;

  @BeforeEach
  void setUp() {
    transaction = new Transaction();
    transaction.setId(1L);
    transaction.setTitle("Test Transaction");
    transaction.setAmount(2000.00);
    transaction.setType(Type.INCOME);
    transaction.setDate(LocalDate.now());

    // Configure outros atributos conforme necessário
  }

  @Test
  void addTransaction() {
    when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

    Transaction createdTransaction = transactionService.addTransaction(transaction);

    assertNotNull(createdTransaction);
    assertEquals(transaction.getId(), createdTransaction.getId());
    assertEquals(transaction.getTitle(), createdTransaction.getTitle());
    assertEquals(transaction.getAmount(), createdTransaction.getAmount());
    assertEquals(transaction.getType(), createdTransaction.getType());
    assertEquals(transaction.getDate(), createdTransaction.getDate());

    verify(transactionRepository, times(1)).save(transaction);
  }

  @Test
  void getAllTransactions() {
    List<Transaction> transactions = Arrays.asList(transaction);
    when(transactionRepository.findAll()).thenReturn(transactions);

    List<Transaction> foundTransactions = transactionService.getAllTransactions();

    assertNotNull(foundTransactions);
    assertEquals(1, foundTransactions.size());
    verify(transactionRepository, times(1)).findAll();
  }

  @Test
  void getByIdTransaction() throws TransactionNotFount {
    when(transactionRepository.findById(transaction.getId())).thenReturn(Optional.of(transaction));

    Transaction foundTransaction = transactionService.getByIdTransaction(transaction.getId());

    assertNotNull(foundTransaction);
    assertEquals(transaction.getTitle(), foundTransaction.getTitle());
    verify(transactionRepository, times(1)).findById(transaction.getId());
  }

  @Test
  void getByIdTransaction_NotFound() {
    when(transactionRepository.findById(transaction.getId())).thenReturn(Optional.empty());

    assertThrows(TransactionNotFount.class, () -> {
      transactionService.getByIdTransaction(transaction.getId());
    });
  }

  @Test
  void removeTransaction() throws TransactionNotFount {
    when(transactionRepository.findById(transaction.getId())).thenReturn(Optional.of(transaction));
    doNothing().when(transactionRepository).delete(transaction);

    String title = transactionService.removeTransaction(transaction.getId());

    assertEquals(transaction.getTitle(), title);
    verify(transactionRepository, times(1)).findById(transaction.getId());
    verify(transactionRepository, times(1)).delete(transaction);
  }
}
