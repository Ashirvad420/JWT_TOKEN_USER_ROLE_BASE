package com.HotelBooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property_user")
public class PropertyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 155)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 125)
    private String lastName;

    @Column(name = "user_name", nullable = false, unique = true, length = 150)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 55)
    private String email;

    @JsonIgnore
    @Column(name = "user_role", nullable = false, length = 55)
    private String userRole;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

}