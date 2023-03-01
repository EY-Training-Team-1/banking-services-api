package com.ey.bankingservicesapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserForm {
    private int id;
    private String name;
    private String email;
    private String pass;

    private List<String> banks;



    @Override
    public String toString() {
        return "UserForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", banks=" + banks +
                '}';
    }
}
