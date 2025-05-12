package org.example.saveuserinfoservice.repository;

import org.example.saveuserinfoservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findByName(String name);

    List<UserModel> findBySurname(String surname);

    List<UserModel> findByNameAndSurname(String name, String surname);

}