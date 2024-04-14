package com.example.entityhistory.user.entity;

import com.example.entityhistory.common.audit.AuditingFields;
import com.example.entityhistory.group.entity.Group;
import com.example.entityhistory.group.entity.GroupUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends AuditingFields {

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

    @Setter
    @OneToMany(mappedBy = "user")
    private List<GroupUser> groupUsers = new ArrayList<>();

    @Builder
    public User(String username, String password, String name, String phoneNumber, String email, Boolean confirmYn, Boolean renewPassword, List<UserRole> userRoles, List<GroupUser> groupUsers, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.confirmYn = confirmYn;
        this.renewPassword = renewPassword;
        this.userRoles = userRoles;
        this.groupUsers = groupUsers;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public String getGroupName() {
        return groupUsers.stream()
                .map(GroupUser::getGroup)
                .map(Group::getName)
                .collect(Collectors.joining(", "));
    }
}
