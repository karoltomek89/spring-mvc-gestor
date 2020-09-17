package com.kt.springmvc.gestor.model.dto;

import com.kt.springmvc.gestor.model.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date dateDeleted;
    private String role;
    private boolean active;
    private Set<User> parents;
    private Set<User> students;
    private Long tempParentId;

}
