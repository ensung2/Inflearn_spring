package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 회원서비스 구현체 설정(널포인트 이슈가 생길 수 있기 때문)
    private final MemberRepository memberRepository = new MemoryMemberRepository();


    @Override
    public void join(Member member) {

        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
