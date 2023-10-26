package hello.servlet.domain.member;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConvurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>();

    // 아이디가 하나씩 증가하는 시퀀스 생성
    private static long sequence = 0L;

    // 싱글톤으로 생성
    @Getter
    private static final MemberRepository instance = new MemberRepository();

    // private로 생성자를 막아야 한다(싱글톤 특징)
    private MemberRepository() {
    }

    // save 제작
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // find 작성
    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store 제거하기
    public void clearStore() {
        store.clear();
    }
}
