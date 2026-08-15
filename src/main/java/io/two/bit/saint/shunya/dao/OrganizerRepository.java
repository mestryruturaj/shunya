package io.two.bit.saint.shunya.dao;

import io.two.bit.saint.shunya.entity.Organizer;
import io.two.bit.saint.shunya.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    boolean existsByUser(User user);
}
