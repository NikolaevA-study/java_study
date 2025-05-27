package org.example.saveuserinfoservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cx_user_contacts")
@Getter
@Setter
public class UserContactsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false, length = 250)
    private String type;

    @Column(name = "value", nullable = false, length = 250)
    private String value;

    @JoinColumn(name = "user_id", table="cx_user_contacts")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private UserModel user;
}
