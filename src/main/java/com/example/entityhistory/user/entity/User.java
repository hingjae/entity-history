package com.example.entityhistory.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    private String username;

    @Setter
    @Column
    private String password; // 비밀번호 변경, 초기화 구분해야함.

    @Setter
    @Column
    private String name;

    @Setter
    @Column
    private String phoneNumber;

    @Setter
    @Column
    private String email;

    @Setter
    @Column
    private Boolean confirmYn = false;

    @Setter
    @Column
    private Boolean renewPassword = false;

    @Setter
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

    @Builder
    public User(String username, String name, String password, String phoneNumber, String email, Boolean confirmYn, boolean renewPassword, List<UserRole> userRoles) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.confirmYn = confirmYn;
        this.renewPassword = renewPassword;
        this.userRoles = userRoles;
    }
}
