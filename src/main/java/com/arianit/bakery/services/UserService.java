package com.arianit.bakery.services;

import com.arianit.bakery.dto.UserDTO;
import com.arianit.bakery.entities.Role;
import com.arianit.bakery.entities.User;
import com.arianit.bakery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService    {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserPassword("122344321");
        user.setUserRole(Role.valueOf("Admin"));
        return userRepository.save(user);
    }

    public Optional<UserDTO> getUserById(Long userId){
        return userRepository.findById(userId).map(UserDTO::fromEntity);
    }

    public List<UserDTO> getAllUser(){
        return userRepository.findAll().stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }

    public List<UserDTO> getUsersById(Iterable<Long> usersId){
        return userRepository.findAllById(usersId).stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }
    

    public boolean existsUserById(Long userId){
        return userRepository.existsById(userId);
    }

    public boolean existsByUserEmail(String userEmail){
        return userRepository.existsByUserEmail(userEmail);
    }

    public long countUsers(){
        return userRepository.count();
    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }

    public void deleteUser(UserDTO userDTO){
        User user = UserDTO.toEntity(userDTO);
        userRepository.delete(user);
    }

    public Optional<UserDTO> getUsersByEmail(String userEmail){
        return userRepository.findByUserEmailIgnoreCase(userEmail).map(UserDTO::fromEntity);
    }


    @Transactional
    public boolean updatePassword(Long userId, String password) {
        int updatedCount = userRepository.updatePassword(userId, password);
        return updatedCount > 0;
    }
    @Transactional
    public boolean updateEmail(Long userId, String email) {
        int updatedCount = userRepository.updateEmail(userId, email);
        return updatedCount > 0;
    }

}
