package example.repository;

import example.entity.Account;
import example.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findTransactionByFromAccountAndId(Account fromAccount,Integer Id);
}
