package com.petarvukcevic.elib.repositories;

import com.petarvukcevic.elib.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

//    @Query(value = """
//            select new com.academy.la9.records.UserAuthRecord(user.username, user.password)
//            from User user
//            where user.username = :username
//            """)
//    UserAuthRecord findByUsername(@Param("username") String username);

    @Query(value = """
            select user from User user
            join fetch user.roles
            where user.username = :username
            """)
    Optional<User> findByUsernameWithRoles(@Param("username") String username);

//    @Query(value = "select user.email from User user where user.username = :username")
//    String findEmailByUsername(@Param("username") String username);

    boolean existsByUsername(String username); // select count(*) > 0 from user where username = :username (true | false)
}
