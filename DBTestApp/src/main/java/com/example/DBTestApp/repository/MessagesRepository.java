package com.example.DBTestApp.repository;

import com.example.DBTestApp.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Integer>  {
}