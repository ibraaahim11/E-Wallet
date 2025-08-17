package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.*;
import com.vois.e_wallet.entities.Transaction;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;

import com.vois.e_wallet.enums.TransactionType;
import com.vois.e_wallet.repositories.TransactionRepository;


import com.vois.e_wallet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService implements GenericService<TransactionDTO, String,Transaction> {
	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	private final WalletService walletService;


	@Override
	public TransactionDTO save(Transaction Transaction) {
		return new TransactionDTO( transactionRepository.save(Transaction));
	}


	@Override
	public Optional<TransactionDTO> findById(String id) {
        return  transactionRepository.findById(id).map(TransactionDTO::new);
	}

	@Override
	public List<TransactionDTO> findAll() {
		return transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
	}

	@Override
	public TransactionDTO update(String id, Transaction Transaction) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		transactionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Transaction found with Id " + id));

		return new TransactionDTO(transactionRepository.save(Transaction));


	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
		transactionRepository.deleteById(id);

	}


	public Wallet deposit(TransactionAction ta) {
		String toUserId = ta.getToUserId();
		Float amount = ta.getAmount();
		Optional<User> toUser = userRepository.findById(toUserId);

		if (toUser.isEmpty()) {
			throw new IllegalArgumentException("No user with id: " + toUserId);
		}

		Wallet toWallet = toUser.get().getWallet();
		if (toWallet == null) {
			throw new IllegalArgumentException("User with id " + toUserId + " does not have a wallet.");
		}



		return walletService.addMoney(toUser.get().getWallet().getId(), amount);



	}


	public Wallet withdraw(TransactionAction ta) {
		String fromUserId = ta.getFromUserId();
		Float amount = ta.getAmount();
		Optional<User> fromUser = userRepository.findById(fromUserId);

		if (fromUser.isEmpty()) {
			throw new IllegalArgumentException("No user with id: " + fromUserId);
		}

		Wallet fromWallet = fromUser.get().getWallet();
		if (fromWallet == null) {
			throw new IllegalArgumentException("User with id " + fromUserId + " does not have a wallet.");
		}

		if (amount > fromWallet.getBalance()) {
			throw new IllegalArgumentException("Cannot withdraw more than balance amount. Withdrawal: " + amount + ", balance: " + fromWallet.getBalance());

		}





		return walletService.subtractMoney(fromUser.get().getWallet().getId(), amount);






	}



    public TransactionDTO makeTransaction(TransactionAction t)
    {


        Wallet toWallet = null;
        Wallet  fromWallet = null;

        switch(t.getType())
        {
            case DEPOSIT:
            {
               toWallet = deposit(t);
                break;
            }
            case WITHDRAWAL:
            {
             fromWallet = withdraw(t);
                break;

            }
            case TRANSFER:{
               toWallet = deposit(t);
               fromWallet = withdraw(t);

            }
        }



        Transaction transaction = Transaction.builder()
                .toWallet(toWallet)
                .fromWallet(fromWallet)
                .type(t.getType())
                .amount(t.getAmount())
                .timestamp(LocalDateTime.now())
                .build();


        Transaction savedT = transactionRepository.save(transaction);



        return new TransactionDTO(savedT);

    }


}





