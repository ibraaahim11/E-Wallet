package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.TransactionAction;
import com.vois.e_wallet.dto.WalletDTO;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.errorHandler.InsufficientBalanceException;
import com.vois.e_wallet.errorHandler.UserHasNoWalletException;
import com.vois.e_wallet.errorHandler.UserNotFoundException;
import com.vois.e_wallet.errorHandler.WalletNotFoundException;
import com.vois.e_wallet.repositories.UserRepository;
import com.vois.e_wallet.repositories.WalletRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

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

		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException("No Wallet found with Id " + walletId));

		wallet.setBalance(wallet.getBalance() + amount);
		return walletRepository.save(wallet);



	}

	public Wallet subtractMoney(String walletId, Float amount) {
		if (walletId == null) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}

		Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException("No Wallet found with Id " + walletId));

		wallet.setBalance(wallet.getBalance() - amount);
		return walletRepository.save(wallet);


	}


    public Wallet deposit(TransactionAction ta) {
        String toUserId = ta.getToUserId();
        Float amount = ta.getAmount();
        Optional<User> toUser = userRepository.findById(toUserId);

        if (toUser.isEmpty()) {
            throw new UserNotFoundException(toUserId);
        }

        Wallet toWallet = toUser.get().getWallet();
        if (toWallet == null) {
            throw new UserHasNoWalletException(toUserId);
        }

        return addMoney(toUser.get().getWallet().getId(), amount);



    }


    public Wallet withdraw(TransactionAction ta) {
        String fromUserId = ta.getFromUserId();
        Float amount = ta.getAmount();
        Optional<User> fromUser = userRepository.findById(fromUserId);

        if (fromUser.isEmpty()) {
            throw new UserNotFoundException(fromUserId);
        }

        Wallet fromWallet = fromUser.get().getWallet();
        if (fromWallet == null) {
            throw new UserHasNoWalletException(fromUserId);
        }

        if (amount > fromWallet.getBalance()) {
            throw new InsufficientBalanceException(amount,fromWallet.getBalance());

        }

        return subtractMoney(fromUser.get().getWallet().getId(), amount);


    }

    @Override
    protected WalletDTO convertToDTO(Wallet entity) {
        return new WalletDTO(entity);
    }

	@Override
	protected Wallet convertToE(WalletDTO dto) {
		return new Wallet(dto);
	}

}




