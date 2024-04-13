package com.example.entityhistory;

import com.example.entityhistory.group.entity.Group;
import com.example.entityhistory.group.repository.GroupRepository;
import com.example.entityhistory.user.controller.form.AddUserForm;
import com.example.entityhistory.user.entity.Role;
import com.example.entityhistory.user.entity.RoleType;
import com.example.entityhistory.user.repository.RoleRepository;
import com.example.entityhistory.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Profile("local-h2")
@RequiredArgsConstructor
@Component
public class DataInit {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        roleRepository.save(Role.builder().roleType(RoleType.GROUP_USER).build());
        roleRepository.save(Role.builder().roleType(RoleType.GROUP_ADMIN).build());
        roleRepository.save(Role.builder().roleType(RoleType.SYSTEM_ADMIN).build());
        groupRepository.save(Group.builder().name("group1").build());

        userService.save(AddUserForm.builder()
                .username("user1")
                .password("pw1")
                .name("name1")
                .email("email1")
                .phoneNumber("phone1")
                .groupName("group1")
                .build());

        userService.save(AddUserForm.builder()
                .username("user2")
                .password("pw2")
                .name("name2")
                .email("email2")
                .phoneNumber("phone2")
                .groupName("group1")
                .build());

        userService.save(AddUserForm.builder()
                .username("user3")
                .password("pw3")
                .name("name3")
                .email("email3")
                .phoneNumber("phone3")
                .groupName("group1")
                .build());

        userService.save(AddUserForm.builder()
                .username("user4")
                .password("pw4")
                .name("name4")
                .email("email4")
                .phoneNumber("phone4")
                .groupName("group1")
                .build());
    }
}
