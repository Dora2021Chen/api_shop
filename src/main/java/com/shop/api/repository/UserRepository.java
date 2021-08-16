package com.shop.api.repository;

import com.shop.api.model.UserDta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDta, Long> {
    @Override
    List<UserDta> findAll();

    @Override
    Optional<UserDta> findById(Long id);

    List<UserDta> findByUsername(String username);

    List<UserDta> findByPhone(String phone);

    List<UserDta> findByEmail(String email);

    @Override
    <S extends UserDta> S save(S s);

    @Override
    void deleteById(Long id);
}
