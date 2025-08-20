package com.vois.e_wallet.dto;

import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.enums.Gender;
import com.vois.e_wallet.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;


    @NotBlank(message = "User's first name must not be blank.")
    private String fName;
    @NotBlank(message = "User's last name must not be blank.")

    private String lName;


    private Integer age;
    @Enumerated(EnumType.STRING)

    private Gender gender;
    @NotBlank(message = "User's email must not be blank.")
    @Email(message = "Invalid user email.")
    private String email;

    private LocalDate joinDate;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "User must have a role")

    private UserRole role;

    private WalletDTO wallet;

    public UserDTO(User user)
    {
        this.id = user.getId();
        this.fName = user.getFName();
        this.lName = user.getLName();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this .joinDate = user.getJoinDate();
        this.role = user.getRole();

        this.wallet = new WalletDTO(user.getWallet());
    }

}
