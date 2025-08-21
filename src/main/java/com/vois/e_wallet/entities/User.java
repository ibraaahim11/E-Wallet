package com.vois.e_wallet.entities;

import com.vois.e_wallet.dto.UserRegisterDTO;
import com.vois.e_wallet.enums.Gender;
import com.vois.e_wallet.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;


	private String fName;
	private String lName;
	@Column(unique = true)
	private String username;
	private String password;
	private Integer age;
	@Enumerated(EnumType.STRING)

	private Gender gender;
	private String email;
	private LocalDate joinDate;
	@Enumerated(EnumType.STRING)

	private UserRole role;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="wallet_id")
	private Wallet wallet;



	public User(UserRegisterDTO userRegisterDTO)
	{

		if (userRegisterDTO != null) {
			this.id = userRegisterDTO.getId();
			this.fName = userRegisterDTO.getFName();
			this.lName = userRegisterDTO.getLName();
			this.age = userRegisterDTO.getAge();
			this.gender = userRegisterDTO.getGender();
			this.email = userRegisterDTO.getEmail();
			this.joinDate = userRegisterDTO.getJoinDate();
			this.role = userRegisterDTO.getRole();
			this.username = userRegisterDTO.getUsername();
			this.password = userRegisterDTO.getPassword();
		}
	}

}
