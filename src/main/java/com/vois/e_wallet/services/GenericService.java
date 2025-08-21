package com.vois.e_wallet.services;

import com.vois.e_wallet.entities.User;

import java.util.List;
import java.util.Optional;

public interface GenericService<DTO, ID, E,ResponseDTO> {
    ResponseDTO save(DTO dto);
    Optional<ResponseDTO> findById(ID id);
    List<ResponseDTO> findAll();
    ResponseDTO update(ID id, DTO dto);
    void deleteById(ID id);
}
