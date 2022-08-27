package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    public MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("Su Hong");

        // when
        Long saveId = memberService.join(member);

        // then
        Member foundMember = memberService.findOne(saveId).get();
        assertThat(foundMember).isEqualTo(member);
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

        // when

        // then
    }

    @Test
    void findOne() {
        // given

        // when

        // then
    }
}