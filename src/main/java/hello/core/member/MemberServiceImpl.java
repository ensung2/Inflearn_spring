package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 회원서비스 구현체 설정
    // 1. appconfig에 따로 설정해둠 (new 지움)
    private final MemberRepository memberRepository;

    // 2. 이후 생성자 생성 (생성자를 통해 memberRepository에 들어갈 값을 설정)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //test용 (단축키 : geti)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
