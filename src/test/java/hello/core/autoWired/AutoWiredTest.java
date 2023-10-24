package hello.core.autoWired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

// 옵션 처리 : 주입할 스프링 빈이 없어도 동작해야 할 때 사용
// 자동 주입 대상을 옵션으로 처리하는 방법은 다음과 같다.
public class AutoWiredTest {
    
    @Test
    void autoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
    
//    임의의 테스트 클래스 만들기
    
    static class TestBean{

        // required = false (기본값은 true)
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1); //아예 호출이 안됨
        }

        // @Nullable 사용
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2); //noBean2 = null
        }

        // Optional<> 사용
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3); //noBean3 = Optional.empty
        }
        
    }
}
