package com.vois.e_wallet.services;

import com.vois.e_wallet.entities.User;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {

	T save(T entity);

	Optional<T> findById(ID id);


	List<T> findAll();

	T update(ID id, T entity);


	void deleteById(ID id);


}
