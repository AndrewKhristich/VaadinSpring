package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users", schema = "public")
public class User implements UserDetails, Serializable {

    @Id
    @Column(name = "username", unique = true)
    @NotNull
    @Size(min = 1, max = 20)
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull
    @JsonIgnore
    @Size(min = 5)
    private String password;

    @Column(name = "non_expired")
    @JsonIgnore
    @Nullable
    private boolean accountNonExpired;

    @Column(name = "non_locked")
    @Nullable
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    @JsonIgnore
    @Nullable
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    @JsonIgnore
    @Nullable
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    @JsonManagedReference
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UserRole> authorities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    @JsonManagedReference
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Issue> issues = new ArrayList<>();

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public List<UserRole> getAuthorities() {
        return authorities;
    }
}
