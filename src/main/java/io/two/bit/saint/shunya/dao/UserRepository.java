package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);

    public Optional<User> findByMobileNumber(String mobileNumber);
}
