package com.swirepay.bankingApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Data
@NoArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId=0;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name="contacts")
    @ElementCollection
    private List<Integer> contacts = new ArrayList<>();

    public void addContact(Integer contactId) {
        if(Objects.isNull(contacts)){
            contacts = new ArrayList<>();
        }
        contacts.add(contactId);
    }


}
