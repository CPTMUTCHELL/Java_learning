package example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "transaction")
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "transaction_date")
    private Timestamp date;

    private BigDecimal sum;
    @ManyToOne()
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = "to_account_id" )
    private Account toAccount;
    @Column(name = "transaction_type")
    private String type;

}
