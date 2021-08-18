package com.swirepay.bankingApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Account")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "balance")
    private Float balance=0F;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime UpdatedAt;


}
