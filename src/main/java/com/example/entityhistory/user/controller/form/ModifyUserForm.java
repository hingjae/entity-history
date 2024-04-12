package com.example.entityhistory.user.controller.form;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ModifyUserForm {
    private final String name;

    private final String phoneNumber;

    private final String email;
}
