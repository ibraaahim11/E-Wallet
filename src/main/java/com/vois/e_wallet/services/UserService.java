package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.UserDTO;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.GenericRepository;
import com.vois.e_wallet.repositories.UserRepository;
import com.vois.e_wallet.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService extends GenericServiceImpl<UserDTO, String, User>{
    private final UserRepository userRepository;

    public UserService(UserRepository ur)
    {
        super(ur);
        this.userRepository = ur;
    }

    @Override
	public UserDTO save(User user) {

		if(user.getWallet() == null)
		{
            user.setWallet(Wallet.builder().balance(BigDecimal.ZERO.floatValue()).user(user).build());

        }

		return new UserDTO(userRepository.save(user));
	}

    @Override
    protected UserDTO convertToDTO(User entity) {
        return new UserDTO(entity);
    }


}




