package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }
    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    public Member findById(Long id){
        return store.get(id); //key값을 넣으면 해당 값을 가져오는 Map 메서드
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
        //[hello.servlet.domain.member.Member@6591f517, hello.servlet.domain.member.Member@345965f2]
        //즉, 객체 주소가 반환된다.
    }

    public void clearStore(){
        store.clear();
    }
}
