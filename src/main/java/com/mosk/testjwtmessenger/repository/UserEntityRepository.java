package com.mosk.testjwtmessenger.repository;

import com.mosk.testjwtmessenger.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByLogin(String login);
}
