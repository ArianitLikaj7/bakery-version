package com.arianit.bakery.dto;

import com.arianit.bakery.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private long userId;
    private String userName;
    private String userEmail;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .userEmail(user.getUserEmail())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .userId(userDTO.getUserId())
                .userName(userDTO.getUserName())
                .userEmail(userDTO.getUserEmail())
                .build();
    }
}
