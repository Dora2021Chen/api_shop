package com.shop.api.repository;

import com.shop.api.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    @Override
    List<UserData> findAll();

    @Override
    Optional<UserData> findById(Long id);

    List<UserData> findByUsername(String username);

    List<UserData> findByPhone(String phone);

    List<UserData> findByEmail(String email);

    @Override
    <S extends UserData> S save(S s);

    @Override
    void deleteById(Long id);
}
