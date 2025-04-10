package org.example.saveuserinfoservice.repository;

import org.example.saveuserinfoservice.model.UserContactsModel;
import org.example.saveuserinfoservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface  UserContactsRepository extends JpaRepository<UserContactsModel, Long> {
    @Modifying
    @Query("delete from UserContactsModel ucm where ucm.user=?1")
    void deleteUserContactsModelByUser(@Param("user") UserModel user);
}
