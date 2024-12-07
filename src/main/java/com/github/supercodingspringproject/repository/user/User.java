package com.github.supercodingspringproject.repository.user;

import com.github.supercodingspringproject.repository.generalUser.GeneralUser;
import com.github.supercodingspringproject.repository.user.AdminUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "phone_num", nullable = false, length = 15)
    private String phoneNum;

    @OneToOne(mappedBy = "user")
    private GeneralUser generalUsers;

    @OneToOne(mappedBy = "user")
    private AdminUser adminUsers;

}