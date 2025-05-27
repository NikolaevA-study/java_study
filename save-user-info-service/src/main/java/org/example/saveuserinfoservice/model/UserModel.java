package org.example.saveuserinfoservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "cx_users")
@Getter
@Setter
public class UserModel {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "surname", nullable = false, length = 250)
    private String surname;

    @Column(name = "age", nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonProperty("contacts")
    private List<UserContactsModel> userContactsModel;
}