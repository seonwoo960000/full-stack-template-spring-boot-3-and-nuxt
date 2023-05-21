package com.devvy.backend.domain.member;

import java.util.UUID;

import com.devvy.backend.common.enums.AuthorityType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "member_authority")
@IdClass(MemberAuthorityPK.class)
public class MemberAuthority {
    @Id
    @Column(name = "member_id")
    private UUID memberId;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority_name", length = 20)
    private AuthorityType authorityType;

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public AuthorityType getAuthorityName() {
        return authorityType;
    }

    public void setAuthorityName(AuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        MemberAuthority that = (MemberAuthority) o;

        if (!memberId.equals(that.memberId)) {return false;}
        return authorityType == that.authorityType;
    }

    @Override
    public int hashCode() {
        int result = memberId.hashCode();
        result = 31 * result + authorityType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MemberAuthority{" +
               "memberId=" + memberId +
               ", authorityName=" + authorityType +
               '}';
    }
}
