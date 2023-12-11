package com.arianit.bakery.repositories;

import com.arianit.bakery.dto.UserDTO;
import com.arianit.bakery.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long userId);

    List<User> findAll();
    @Query("SELECT bp FROM User bp WHERE bp.userId IN :ids")
    List<User> findAllById(@Param("ids") Iterable<Long> usersId);
    boolean existsById(Long userId);
    boolean existsByUserEmail(String userEmail);

    long count();
    void deleteById(Long userId);

    void delete(User user);
    Optional<User> findByUserEmailIgnoreCase(String userEmail);

    Optional<User> findByUserName(String username);

    @Modifying
    @Query("UPDATE User u SET u.userPassword = :password WHERE u.userId = :userId")
    int updatePassword(@Param("userId") Long userId, @Param("password") String password);

    @Modifying
    @Query("UPDATE User u SET u.userEmail = :email WHERE u.userId = :userId")
    int updateEmail(@Param("userId") Long userId, @Param("email") String email);


}
