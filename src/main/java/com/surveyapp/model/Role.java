package com.surveyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_id")
    private int roleId;

    @Column(name="role_name")
    private String roleName;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "userRoles")
    private List<User> users;
}
