package com.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Table(name = "members")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String nickname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    @CreationTimestamp
    private LocalDateTime createAt;

    private String role;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return AuthorityUtils.createAuthorityList(role);
    }
}
