package org.example.saveuserinfoservice.repository;

import org.example.saveuserinfoservice.model.UserContactsModel;
import org.example.saveuserinfoservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  UserContactsRepository extends JpaRepository<UserContactsModel, Long> {
    List<UserContactsModel> findByUser(UserModel user);
}
