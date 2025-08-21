package com.vois.e_wallet.dto;

import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.enums.Gender;
import com.vois.e_wallet.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    private String id;


    @NotBlank(message = "User's first name must not be blank.")
    private String fName;
    @NotBlank(message = "User's last name must not be blank.")

    private String lName;

    @NotBlank(message = "User's username must not be blank.")
    private String username;

    @NotBlank(message = "User's password must not be blank.")
    private String password;

    private Integer age;
    @Enumerated(EnumType.STRING)


    private Gender gender;
    @NotBlank(message = "User's email must not be blank.")
    @Email(message = "Invalid user email.")
    private String email;

    private LocalDate joinDate;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "User must have a role. USER or ADMIN")

    private UserRole role;


    public UserRegisterDTO(User user)
    {
        if( user != null)
        {
            this.id = user.getId();
            this.fName = user.getFName();
            this.lName = user.getLName();
            this.age = user.getAge();
            this.gender = user.getGender();
            this.email = user.getEmail();
            this .joinDate = user.getJoinDate();
            this.role = user.getRole();
            this.password = user.getPassword();
            this.username = user.getUsername();

        }




    }

}
