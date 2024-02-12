package com.CheckYourUnderstanding.Task2.entity;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccountEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
