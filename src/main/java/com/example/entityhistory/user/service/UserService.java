package com.example.entityhistory.user.service;

import com.example.entityhistory.group.entity.Group;
import com.example.entityhistory.group.entity.GroupUser;
import com.example.entityhistory.group.repository.GroupRepository;
import com.example.entityhistory.group.repository.GroupUserRepository;
import com.example.entityhistory.user.controller.form.AddUserForm;
import com.example.entityhistory.user.controller.form.ModifyUserForm;
import com.example.entityhistory.user.entity.Role;
import com.example.entityhistory.user.entity.RoleType;
import com.example.entityhistory.user.entity.User;
import com.example.entityhistory.user.entity.UserRole;
import com.example.entityhistory.user.repository.RoleRepository;
import com.example.entityhistory.user.repository.UserRepository;
import com.example.entityhistory.user.repository.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;
    private final GroupUserRepository groupUserRepository;

    @Transactional
    public User save(AddUserForm addUserForm) {
        Role role = roleRepository.findByRoleType(RoleType.GROUP_USER);
        User user = userRepository.save(createUser(addUserForm));
        userRoleRepository.save(createUserRole(user, role));
        Group group = groupRepository.findByName(addUserForm.getGroupName())
                .orElseThrow(EntityNotFoundException::new);
        groupUserRepository.save(createGroupUser(group, user));
        return user;
    }

    private GroupUser createGroupUser(Group group, User user) {
        return GroupUser.builder()
                .group(group)
                .user(user)
                .build();
    }

    private User createUser(AddUserForm form) {
        return User.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .phoneNumber(form.getPhoneNumber())
                .email(form.getEmail())
                .confirmYn(false)
                .renewPassword(false)
                .createdBy(form.getUsername())
                .modifiedBy(form.getUsername())
                .build();
    }

    private UserRole createUserRole(User user, Role role) {
        return UserRole.builder()
                .user(user)
                .role(role)
                .build();
    }

    public List<User> findAllWithGroups() {
        return userRepository.findAllWithGroups();
    }

    public User findById(String username) {
        return userRepository.findByUsernameWithGroups(username)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void modify(String username, ModifyUserForm form) {
        User user = findById(username);

        user.setName(form.getName());
        user.setPhoneNumber(form.getPhoneNumber());
        user.setEmail(form.getEmail());
    }
}
