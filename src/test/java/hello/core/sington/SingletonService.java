package hello.core.sington;

public class SingletonService {

    // static final : 딱 하나만 존재하게 됌
    private static final SingletonService instance = new SingletonService();

    // 조회할 떄 사용 (상단 instance를 꺼낼 수 있는건 해당 getInstance()만 할 수 있음!!
    // 해당 메서드를 사용하면 항상 같은 인스턴스를 반환한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // private : 다른곳에선 사용할 수 없다!! (외부에서 new 키워드로 객체 인스턴스가 생성되는걸 막는다)
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
