package com.example.DBTestApp.repository;

import com.example.DBTestApp.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long>  {


    @Query("select m.id from Messages m where m.id = :id")
    Long checkIdIsExists(Long id);

}