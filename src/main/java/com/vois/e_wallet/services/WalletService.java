package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.TransactionAction;
import com.vois.e_wallet.dto.WalletDTO;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.UserRepository;
import com.vois.e_wallet.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletService extends GenericServiceImpl<WalletDTO,String,Wallet> {
	private final WalletRepository walletRepository;
    private final UserRepository userRepository;


    WalletService(WalletRepository wr, UserRepository ur)
    {
        super(wr);
        this.walletRepository = wr;
        this.userRepository = ur;
    }

    public Wallet addMoney(String walletId, Float amount) {
		if (walletId == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("No Wallet found with Id " + walletId));

		wallet.setBalance(wallet.getBalance() + amount);
		return walletRepository.save(wallet);



	}

	public Wallet subtractMoney(String walletId, Float amount) {
		if (walletId == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("No Wallet found with Id " + walletId));

		wallet.setBalance(wallet.getBalance() - amount);
		return walletRepository.save(wallet);


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

        return addMoney(toUser.get().getWallet().getId(), amount);



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

        return subtractMoney(fromUser.get().getWallet().getId(), amount);


    }

    @Override
    protected WalletDTO convertToDTO(Wallet entity) {
        return new WalletDTO(entity);
    }

}




