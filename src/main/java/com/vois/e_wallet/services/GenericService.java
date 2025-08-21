package com.vois.e_wallet.services;

import com.vois.e_wallet.entities.User;

import java.util.List;
import java.util.Optional;

public interface GenericService<DTO, ID, E> {
    DTO save(DTO dto);
    Optional<DTO> findById(ID id);
    List<DTO> findAll();
    DTO update(ID id, DTO dto);
    void deleteById(ID id);
}
