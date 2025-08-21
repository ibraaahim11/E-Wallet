package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.UserDTO;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.enums.UserRole;
import com.vois.e_wallet.repositories.GenericRepository;
import com.vois.e_wallet.repositories.UserRepository;
import com.vois.e_wallet.repositories.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.EnumUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
	public UserDTO save(UserDTO userDTO) {


		User user = convertToE(userDTO);

		if(user.getWallet() == null )
		{
            user.setWallet(Wallet.builder().balance(BigDecimal.ZERO.floatValue()).user(user).build());

        }
		else if(user.getWallet().getId() == null)

		{
			user.setWallet(Wallet.builder().balance(BigDecimal.ZERO.floatValue()).user(user).build());

		}





		return convertToDTO(userRepository.save(user));
	}
	@Override
	public UserDTO update(String id, UserDTO userDTO)
	{
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("Entity not found");
		}

		Wallet wallet = userRepository.findById(id).get().getWallet();

		User user = convertToE(userDTO);
		user.setWallet(wallet);
		return convertToDTO(userRepository.save(user));



	}

    @Override
    protected UserDTO convertToDTO(User entity) {
        return new UserDTO(entity);
    }

	@Override
	protected User convertToE(UserDTO dto) {
		return new User(dto);
	}

}




