package com.example.entityhistory.user.repository;

import com.example.entityhistory.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u " +
            "from User u " +
            "join fetch u.userRoles ur " +
            "join fetch ur.role r " +
            "where u.username = :username")
    Optional<User> findUserWithRolesByUsername(@Param("username") String username);

    @Query("select u " +
            "from User u " +
            "join fetch u.groupUsers gu " +
            "join fetch gu.group g")
    List<User> findAllWithGroups();

    @Query("select u " +
            "from User u " +
            "join fetch u.groupUsers gu " +
            "join fetch gu.group " +
            "where u.username = :username")
    Optional<User> findByUsernameWithGroups(@Param("username") String username);
}
