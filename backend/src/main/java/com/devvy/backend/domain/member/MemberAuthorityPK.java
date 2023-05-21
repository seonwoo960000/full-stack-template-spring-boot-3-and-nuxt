package com.devvy.backend.domain.member;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.devvy.backend.common.enums.AuthorityType;

public class MemberAuthorityPK implements Serializable {
    private UUID memberId;
    private AuthorityType authorityType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        MemberAuthorityPK that = (MemberAuthorityPK) o;

        if (!Objects.equals(memberId, that.memberId)) {return false;}
        return authorityType == that.authorityType;
    }

    @Override
    public int hashCode() {
        int result = memberId != null ? memberId.hashCode() : 0;
        result = 31 * result + (authorityType != null ? authorityType.hashCode() : 0);
        return result;
    }
}
