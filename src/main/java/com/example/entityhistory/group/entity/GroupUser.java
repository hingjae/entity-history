package com.example.entityhistory.group.entity;

import com.example.entityhistory.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class GroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupUserSn;

    @ManyToOne
    @JoinColumn(name = "group_sn")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @Builder
    public GroupUser(Long groupUserSn, Group group, User user) {
        this.groupUserSn = groupUserSn;
        this.group = group;
        this.user = user;
    }
}
