package com.vois.e_wallet.repositories;

import com.vois.e_wallet.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WalletRepository extends GenericRepository<Wallet,String> {
}
