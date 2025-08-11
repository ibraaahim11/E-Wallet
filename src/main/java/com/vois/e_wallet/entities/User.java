package com.vois.e_wallet.entities;

import com.vois.e_wallet.enums.Gender;
import com.vois.e_wallet.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="USERS")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String fName;
	private String lName;
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




}
