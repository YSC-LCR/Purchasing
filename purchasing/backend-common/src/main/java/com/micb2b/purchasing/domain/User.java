package com.micb2b.purchasing.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jay
 * @date 2019-11-19
 */
@Entity
@Getter
@Setter
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    private String username;

    private String avatar;

//    @NotBlank
//    @Pattern(regexp = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}",message = "�撘�秤")
    private String email;

//    @NotNull
    private Boolean enabled;

    private String password;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "last_password_reset_time")
    private Date lastPasswordResetTime;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
//                ", avatar='" + avatar + '\'' +
//                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", lastPasswordResetTime=" + lastPasswordResetTime +
                '}';
    }

    public @interface Update {}
}