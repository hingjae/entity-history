package com.example.entityhistory.user.service;

import com.example.entityhistory.common.config.TestJpaConfig;
import com.example.entityhistory.group.entity.Group;
import com.example.entityhistory.group.repository.GroupRepository;
import com.example.entityhistory.user.controller.form.AddUserForm;
import com.example.entityhistory.user.entity.Role;
import com.example.entityhistory.user.entity.RoleType;
import com.example.entityhistory.user.entity.User;
import com.example.entityhistory.user.repository.RoleRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@Import(TestJpaConfig.class)
@SpringBootTest(properties = {"spring.cache.type=none"})
class UserServiceTest {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void DataInit() {
        roleRepository.save(Role.builder().roleType(RoleType.GROUP_USER).build());
        roleRepository.save(Role.builder().roleType(RoleType.GROUP_ADMIN).build());
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

        em.flush();
        em.clear();
    }

    @Transactional
    @Test
    public void 회원가입을한다() {
        AddUserForm form = AddUserForm.builder()
                .username("user5")
                .password("pw5")
                .name("name5")
                .email("email5")
                .phoneNumber("01012341234")
                .groupName("group1")
                .build();

        User result = userService.save(form);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("user5");
        assertThat(result.getName()).isEqualTo("name5");
        assertThat(result.getEmail()).isEqualTo("email5");
        assertThat(result.getPhoneNumber()).isEqualTo("01012341234");

        // TODO : nullpointerException 해결하기
        // assertThat(result.getGroupName()).isEqualTo("group1");
    }

    @Transactional
    @Test
    public void 회원정보와_함께_그룹정보를_가져온다() {
        List<User> users = userService.findAllWithGroups();
        User user = users.get(0);

        assertThat(user).isNotNull();
        assertThat(user.getGroupName()).isEqualTo("group1");
    }
}
