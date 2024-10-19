package com.survey.api.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 12, message = "El nombre de usuario debe tener entre 3 y 12 caracteres")
    @Column(unique = true)
    private String username;

    @Email
    @Column(unique = true)
    private String email;

    @Size(min = 6, message = "La contraseña debe tener minimo 6 caracteres")
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @ManyToMany(mappedBy = "users")
    private List<Survey> surveys;

    @OneToMany(mappedBy = "user")
    private List<UserQuestion> userQuestions;

    public void updateUser(User user){
        this.setId(id);
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setSurveys(this.getSurveys());
        this.setRole(this.getRole());
    }
}
