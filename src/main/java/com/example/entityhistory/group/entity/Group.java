package com.example.entityhistory.group.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "groups")
@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupSn;

    @Column
    private String name;

    @Builder
    public Group(Long groupSn, String name) {
        this.groupSn = groupSn;
        this.name = name;
    }
}
