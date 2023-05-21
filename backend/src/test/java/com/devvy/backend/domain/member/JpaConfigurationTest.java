package com.devvy.backend.domain.member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.devvy.backend.common.enums.AuthorizationType;
import com.devvy.backend.common.enums.LangKey;
import com.devvy.backend.infrastructure.config.JpaAuditConfiguration;

@DataJpaTest
@Import(JpaAuditConfiguration.class)
class JpaConfigurationTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository);
        authorityRepository.saveAll(Authority.getAllAuthorities());
    }

    @Test
    void AbstractAuditingEntityWithUUID_id_creation_test() {
        Member member = createFixture();
        var savedMember = memberService.save(member);
        assertThat(savedMember.getId()).isNotNull();
        assertThat(memberService.findById(savedMember.getId())).isPresent();
    }

    @Test
    void AbstractAuditingEntityWithUUID_equality_test() {
        var savedMember = memberService.save(createFixture());
        var retrievedMember = memberService.findById(savedMember.getId()).get();

        assertThat(savedMember.getId()).isEqualTo(retrievedMember.getId());
    }

    @Test
    void AbstractAuditingEntityWithUUID_unmodifiable_field_test() {
        var memberBeforeUpdate = memberService.save(createFixture());

        var memberToUpdate = memberService.findById(memberBeforeUpdate.getId()).get();
        var updatedMember = memberService.save(memberToUpdate);

        // TODO: AuditorAwareImpl should be implemented
        // assertThat(updatedMember.getCreatedBy()).isEqualTo(memberBeforeUpdate.getCreatedBy());
        // assertThat(updatedMember.getLastModifiedBy()).isEqualTo(memberToUpdate.getLastModifiedBy());
        assertThat(updatedMember.getCreatedDate()).isEqualTo(memberBeforeUpdate.getCreatedDate());
        assertThat(updatedMember.getLastModifiedDate()).isEqualTo(memberToUpdate.getLastModifiedDate());
    }

    private Member createFixture() {
        Member member = new Member();
        member.setLogin(String.valueOf(UUID.randomUUID()));
        member.setAuthorizationType(AuthorizationType.NONE);
        member.setPassword("password_hashed");
        member.setFirstName("Seon Woo");
        member.setLastName("Kim");
        member.setEmail("devvy@gmail.com");
        member.setActivated(true);
        member.setLangKey(LangKey.KO);
        member.setImageUrl("http://profile.com");
        member.setActivationKey("activationKey");
        member.setResetKey("resetKey");
        member.setResetDate(Instant.now());

        return member;
    }
}
