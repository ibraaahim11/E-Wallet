package com.vois.e_wallet.repositories;

import com.vois.e_wallet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User,String> {

	
}
