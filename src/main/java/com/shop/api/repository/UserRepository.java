package com.shop.api.repository;

import com.shop.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    <S extends User> S save(S s);

    @Override
    void deleteById(Long id);
}
