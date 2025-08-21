package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.UserRegisterDTO;
import com.vois.e_wallet.dto.UserResponseDTO;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;
import com.vois.e_wallet.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService extends GenericServiceImpl<UserRegisterDTO, String, User,UserResponseDTO>{
    private final UserRepository userRepository;

    public UserService(UserRepository ur)
    {
        super(ur);
        this.userRepository = ur;
    }

    @Override
	public UserResponseDTO save(UserRegisterDTO userRegisterDTO) {


		User user = convertToE(userRegisterDTO);

		if(user.getWallet() == null )
		{
            user.setWallet(Wallet.builder().balance(BigDecimal.ZERO.floatValue()).user(user).build());

        }
		else if(user.getWallet().getId() == null)

		{
			user.setWallet(Wallet.builder().balance(BigDecimal.ZERO.floatValue()).user(user).build());

		}





		return convertToResponseDTO(userRepository.save(user));
	}
	@Override
	public UserResponseDTO update(String id, UserRegisterDTO userRegisterDTO)
	{
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("Entity not found");
		}

		Wallet wallet = userRepository.findById(id).get().getWallet();

		User user = convertToE(userRegisterDTO);
		user.setWallet(wallet);
		return convertToResponseDTO(userRepository.save(user));



	}

    @Override
    protected UserRegisterDTO convertToDTO(User entity) {
        return new UserRegisterDTO(entity);
    }

	protected UserResponseDTO convertToResponseDTO(User entity) {
		return new UserResponseDTO(entity);
	}

	@Override
	protected User convertToE(UserRegisterDTO dto) {
		return new User(dto);
	}

}




