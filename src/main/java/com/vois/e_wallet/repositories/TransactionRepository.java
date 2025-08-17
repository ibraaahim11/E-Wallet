package com.vois.e_wallet.repositories;

import com.vois.e_wallet.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends GenericRepository<Transaction,String> {


	@Query(value = "SELECT * FROM TRANSACTIONS WHERE type = 'REFUND'",nativeQuery = true)
	List<Transaction> findAllRefunds();
}
