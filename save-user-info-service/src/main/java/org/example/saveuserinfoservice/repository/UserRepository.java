package org.example.saveuserinfoservice.repository;

import org.example.saveuserinfoservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query("select um from UserModel um where um.name=?1")
    List<UserModel> findUsersModelByName(@Param("name") String name);

    @Query("select um from UserModel um where um.surname=?1")
    List<UserModel> findUsersModelBySurname(@Param("surname") String surname);

    @Query("select um from UserModel um where um.name=?1 and um.surname=?2")
    List<UserModel> findUsersModelByNameAndSurname(@Param("name") String name,@Param("surname") String surname);

    @Query("select um from UserModel um where um.id=?1")
    UserModel findUserModelById(@Param("id") Long id);
}