package net.casim.jbd.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String name;

    private String surname;

    private BigDecimal creditLimit;

    private BigDecimal usedCreditLimit = BigDecimal.ZERO;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Customer(String name, String surname, BigDecimal creditLimit, User user) {
        this.name = name;
        this.surname = surname;
        this.creditLimit = creditLimit;
        this.user = user;
    }

    public void addUsedCredit(BigDecimal amount) {
        this.usedCreditLimit = this.usedCreditLimit.add(amount);
    }

    public void subtractUsedCredit(BigDecimal amount) {
        this.usedCreditLimit = this.usedCreditLimit.subtract(amount);
    }
}
