package com.vois.e_wallet.services;

import com.vois.e_wallet.repositories.GenericRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<DTO,ID,E,ResponseDTO> implements GenericService<DTO,ID,E,ResponseDTO> {


    protected final GenericRepository<E, ID> repository;


    @Override
    public ResponseDTO save(DTO dto) {

        E savedEntity = repository.save(convertToE(dto));
        return convertToResponseDTO(savedEntity);
    }

    @Override
    public Optional<ResponseDTO> findById(ID id) {
        return repository.findById(id).map(this::convertToResponseDTO);
    }

    @Override
    public List<ResponseDTO> findAll() {
        return repository.findAll().stream().map(this::convertToResponseDTO).toList();
    }

    @Override
    public ResponseDTO update(ID id, DTO dto) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entity not found");
        }
        E updatedEntity = repository.save(convertToE(dto));
        return convertToResponseDTO(updatedEntity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    protected abstract DTO convertToDTO(E entity);
    protected abstract ResponseDTO convertToResponseDTO(E entity);

    protected abstract E convertToE(DTO dto);

}

