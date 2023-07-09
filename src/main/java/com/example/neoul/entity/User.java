package com.example.neoul.entity;


import com.example.neoul.global.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;


@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    private String social;

    private Integer status;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private List<Authority> authorities;



    //TODO dto로 수정하기 나중에
    public static User toSocialLoginUser(String email, String social, String name) {
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(email)
                .name(name)
                .password("")  //소셜로그인은 비밀번호x
                .imageUrl("이미지url")
                .authorities(Collections.singletonList(authority))
                .social(social)
                .status(1)
                .build();

        return user;
    }

}
