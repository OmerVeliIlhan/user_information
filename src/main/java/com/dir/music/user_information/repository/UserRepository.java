package com.dir.music.user_information.repository;

import com.dir.music.user_information.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameAndSurname(String userName,String userSurname);
}
