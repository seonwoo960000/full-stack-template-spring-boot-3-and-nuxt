package com.devvy.backend.domain.member;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.devvy.backend.common.enums.AuthorityType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private AuthorityType name;

    public AuthorityType getName() {
        return name;
    }

    public void setName(AuthorityType name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        return Objects.equals(name, ((Authority) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Authority{" +
               "name='" + name + '\'' +
               "}";
    }

    public static Authority getDefaultAuthority() {
        var authority = new Authority();
        authority.setName(AuthorityType.USER);
        return authority;
    }

    public static List<Authority> getAllAuthorities() {
        var authorities = new ArrayList<Authority>();
        for (AuthorityType authorityType : AuthorityType.values()) {
            var authority = new Authority();
            authority.setName(authorityType);
            authorities.add(authority);
        }

        return authorities;
    }
}
