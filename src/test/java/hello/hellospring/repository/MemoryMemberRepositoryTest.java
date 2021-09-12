package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 각 메소드가 끝날때 실행되는 메소드 (AfterEach)
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");   //회원이름

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //Optional이기 때문에 get을 써줘야 Member가 반환됨.

        // 같은지 다른지 테스트. 객체 생성한 member와 db저장소에서 가져온 member가 같은지.
        // @Test 기능으로 test할수 있다.
//        Assertions.assertEquals(member, result);  // 객체생성한 member랑 db에서 꺼낸 result랑 똑같으면 test 정상.다른 객체면 에러뜸. jupiter의 Assertions
        assertThat(member).isEqualTo(result);    // assertj.core의 Assertions
    }

    @Test
    public void findByName11() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
