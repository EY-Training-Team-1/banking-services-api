package com.ey.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "u_id")
    private Users userId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Operation operation;
    @ManyToOne
    @JoinColumn(name = "account_no", referencedColumnName = "b_id")
    private Bank accountNum;
    private Integer transferToAcc;

}
