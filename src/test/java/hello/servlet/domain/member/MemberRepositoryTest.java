package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("kim", 20);

        //when
        Member saveMember = memberRepository.save(member);//sequence++ , store<member.Id, member>

        //then
        Member findMember = memberRepository.findById(saveMember.getId()); //멤버가 반환
        Assertions.assertThat(findMember).isEqualTo(saveMember);

    }

    @Test
    void findAll(){
        Member member1 = new Member("member1", 20);
        Member member2  = new Member("member2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        //System.out.println(result);
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}