package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("Su Hong");

        // when
        Long saveId = memberService.join(member);

        // then
        Member foundMember = memberService.findOne(saveId).get();
        assertThat(foundMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void duplicateJoin() {
        // given
        Member member1 = new Member();
        member1.setName("Su Hong");

        Member member2 = new Member();
        member2.setName("Su Hong");

        // when
        Long saveId1 = memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
    }

    @Test
    void findMembers() {
        // given
        Member member = new Member();
        member.setName("suhong");
        memberService.join(member);

        // when
        List<Member> result = memberService.findMembers();

        // then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void findOne() {
        // given
        Member member = new Member();
        member.setName("suhong");
        Long joinedId = memberService.join(member);

        // when
        Member result = memberService.findOne(joinedId).get();

        // then
        assertThat(result.getName()).isEqualTo(member.getName());
    }
}
