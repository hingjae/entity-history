package com.example.entityhistory.group.repository;

import com.example.entityhistory.group.entity.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
}
