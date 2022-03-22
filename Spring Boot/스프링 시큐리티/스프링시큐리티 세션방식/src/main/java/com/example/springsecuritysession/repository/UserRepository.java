package com.example.springsecuritysession.repository;

import com.example.springsecuritysession.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nickname);

}

