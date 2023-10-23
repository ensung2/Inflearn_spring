package hello.core.sington;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
       ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        // ThreadA : A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA",10000);
        // ThreadB : B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);
        
        // ThreadA : 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

        // 문제!!! statefulService는 같은 객체 (마지막에 넣은 인스턴스가 출력됨)

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}