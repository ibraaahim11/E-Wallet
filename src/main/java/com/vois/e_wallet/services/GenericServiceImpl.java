package com.vois.e_wallet.services;

import com.vois.e_wallet.repositories.GenericRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<DTO,ID,E> implements GenericService<DTO,ID,E> {


    protected final GenericRepository<E, ID> repository;


    @Override
    public DTO save(DTO dto) {

        E savedEntity = repository.save(convertToE(dto));
        return convertToDTO(savedEntity);
    }

    @Override
    public Optional<DTO> findById(ID id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<DTO> findAll() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }

    @Override
    public DTO update(ID id, DTO dto) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entity not found");
        }
        E updatedEntity = repository.save(convertToE(dto));
        return convertToDTO(updatedEntity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    protected abstract DTO convertToDTO(E entity);
    protected abstract E convertToE(DTO dto);

}

