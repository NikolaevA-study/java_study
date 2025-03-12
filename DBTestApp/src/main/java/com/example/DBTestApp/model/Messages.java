package com.example.DBTestApp.model;

import jakarta.persistence.*;


@Entity // указывает, что данный бин (класс) является сущностью
//@SequenceGenerator(name = "messages_seq", sequenceName = "messages_seq")
@Table(name = "messages") //указывает на имя таблицы, которая будет отображаться в этой сущности.
@SequenceGenerator(name = "messages_seq", sequenceName = "messages_seq",allocationSize = 1)
public class Messages {

    @Id
    @Column(name = "id") //на имя колонки, которая отображается в свойство сущности
    @GeneratedValue(generator = "messages_seq", strategy = GenerationType.SEQUENCE)
    //указывает, что данное свойство будет создаваться согласно указанной стратегии
    private Long id;

    @Column(name = "message") //на имя колонки, которая отображается в свойство сущности
    private String message;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}