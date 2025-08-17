package com.vois.e_wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<Entity, Id> extends JpaRepository<Entity,Id> {
}
