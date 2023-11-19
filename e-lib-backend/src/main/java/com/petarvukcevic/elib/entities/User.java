package com.petarvukcevic.elib.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String username;
    private String password;
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_at")
    @CreationTimestamp()
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp()
    private Date updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role)
    {
        if (role != null) {
            this.getRoles().add(role);
        }
    }
}
