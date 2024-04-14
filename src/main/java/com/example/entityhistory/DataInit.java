package com.example.entityhistory;

import com.example.entityhistory.group.entity.Group;
import com.example.entityhistory.group.entity.GroupUser;
import com.example.entityhistory.group.repository.GroupRepository;
import com.example.entityhistory.group.repository.GroupUserRepository;
import com.example.entityhistory.user.entity.Role;
import com.example.entityhistory.user.entity.RoleType;
import com.example.entityhistory.user.entity.User;
import com.example.entityhistory.user.entity.UserRole;
import com.example.entityhistory.user.repository.RoleRepository;
import com.example.entityhistory.user.repository.UserRepository;
import com.example.entityhistory.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Profile("local-h2")
@RequiredArgsConstructor
@Component
public class DataInit {
    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    private final GroupUserRepository groupUserRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)

    public void initData() {
        roleRepository.save(Role.builder().roleType(RoleType.GROUP_USER).build());
        roleRepository.save(Role.builder().roleType(RoleType.GROUP_ADMIN).build());
        roleRepository.save(Role.builder().roleType(RoleType.SYSTEM_ADMIN).build());
        Role groupUserRole = roleRepository.findByRoleType(RoleType.GROUP_USER);
        Role systemAdminRole = roleRepository.findByRoleType(RoleType.SYSTEM_ADMIN);
        Group group1 = groupRepository.save(Group.builder().name("group1").build());

        LocalDateTime now = LocalDateTime.now();

        User user1 = userRepository.save(
                User.builder()
                        .username("user1")
                        .password(passwordEncoder.encode("pw1"))
                        .name("name1")
                        .email("email1")
                        .phoneNumber("phone1")
                        .createdAt(now)
                        .createdBy("SYSTEM_ADMIN")
                        .modifiedAt(now)
                        .modifiedBy("SYSTEM_ADMIN")
                        .build()
        );

        User user2 = userRepository.save(
                User.builder()
                        .username("user2")
                        .password(passwordEncoder.encode("pw2"))
                        .name("name2")
                        .email("email2")
                        .phoneNumber("phone2")
                        .createdAt(now)
                        .createdBy("SYSTEM_ADMIN")
                        .modifiedAt(now)
                        .modifiedBy("SYSTEM_ADMIN")
                        .build()
        );

        User user3 = userRepository.save(
                User.builder()
                        .username("user3")
                        .password(passwordEncoder.encode("pw3"))
                        .name("name3")
                        .email("email3")
                        .phoneNumber("phone3")
                        .createdAt(now)
                        .createdBy("SYSTEM_ADMIN")
                        .modifiedAt(now)
                        .modifiedBy("SYSTEM_ADMIN")
                        .build()
        );

        User user4 = userRepository.save(
                User.builder()
                        .username("user4")
                        .password(passwordEncoder.encode("pw4"))
                        .name("name4")
                        .email("email4")
                        .phoneNumber("phone4")
                        .createdAt(now)
                        .createdBy("SYSTEM_ADMIN")
                        .modifiedAt(now)
                        .modifiedBy("SYSTEM_ADMIN")
                        .build()
        );

        User systemAdmin = userRepository.save(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .name("admin")
                        .email("admin")
                        .phoneNumber("01012341234")
                        .createdAt(now)
                        .createdBy("SYSTEM_ADMIN")
                        .modifiedAt(now)
                        .modifiedBy("SYSTEM_ADMIN")
                        .build()
        );

        groupUserRepository.save(
                GroupUser.builder()
                        .user(user1)
                        .group(group1)
                        .build()
        );

        groupUserRepository.save(
                GroupUser.builder()
                        .user(user2)
                        .group(group1)
                        .build()
        );

        groupUserRepository.save(
                GroupUser.builder()
                        .user(user3)
                        .group(group1)
                        .build()
        );

        groupUserRepository.save(
                GroupUser.builder()
                        .user(user4)
                        .group(group1)
                        .build()
        );

        userRoleRepository.save(
                UserRole.builder()
                        .user(user1)
                        .role(groupUserRole)
                        .build()
        );

        userRoleRepository.save(
                UserRole.builder()
                        .user(user2)
                        .role(groupUserRole)
                        .build()
        );

        userRoleRepository.save(
                UserRole.builder()
                        .user(user3)
                        .role(groupUserRole)
                        .build()
        );

        userRoleRepository.save(
                UserRole.builder()
                        .user(user4)
                        .role(groupUserRole)
                        .build()
        );

        userRoleRepository.save(
                UserRole.builder()
                        .user(systemAdmin)
                        .role(systemAdminRole)
                        .build()
        );
    }
}
