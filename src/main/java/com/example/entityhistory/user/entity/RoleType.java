package com.example.entityhistory.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    GROUP_USER("그룹사용자"), GROUP_ADMIN("그룹관리자"), SYSTEM_ADMIN("시스템관리자");

    private final String description;
}
