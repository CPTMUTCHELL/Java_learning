package example.repository;

import example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


public interface AccountRepository extends JpaRepository<Account,Integer> {
    @Query("select a from Account a where a.name=?1")
    Account findByName(String name);
    @Modifying
    @Transactional
    @Query("update Account set balance=?1 where name=?2")
    void updateBalance (BigDecimal balance, String name);
}
