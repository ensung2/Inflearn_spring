//package hello.core.lifecycle;
//
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//
//// 1번째 방법 : 인터페이스 사용
//public class NetworkClient implements InitializingBean, DisposableBean {
//
//    private String url;
//
//    public NetworkClient() {
//        System.out.println("첫번째 : 클라이언트에서 호출");
//        System.out.println("생성자 호출, url = " + url); // 호출
////        connect();  // 다음 연결
////        call("초기화 연결 메세지"); // 서버로 보냄
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//
//    }
//
//    // 서비스 시작시 호출
//
//    public void connect() {
//        System.out.println("connect  = " + url);
//
//    }
//
//    public void call(String message) {
//        System.out.println("call = " + url + " message = " + message );
//    }
//
//    // 서비스 종료시 호출
//
//    public void disconnect(){
//        System.out.println("close = " + url);
//    }
//
//    // 의존관계 셋팅이 끝나면 호출
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("두번째 : NetworkClinet.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메세지2");
//
//}
//    // 상단이 끝나면 호출
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("세번째 : NetworkClinet.destroy");
//        disconnect();
//    }
//}

/*========================================================================================*/
//
//package hello.core.lifecycle;
//
//// 2번째 방법 : init 사용
//public class NetworkClient {
//
//    private String url;
//
//    public NetworkClient() {
//        System.out.println("첫번째 : 클라이언트에서 호출");
//        System.out.println("생성자 호출, url = " + url); // 호출
////        connect();  // 다음 연결
////        call("초기화 연결 메세지"); // 서버로 보냄
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//
//    }
//
//    // 서비스 시작시 호출
//
//    public void connect() {
//        System.out.println("connect  = " + url);
//
//    }
//
//    public void call(String message) {
//        System.out.println("call = " + url + " message = " + message );
//    }
//
//    // 서비스 종료시 호출
//
//    public void disconnect(){
//        System.out.println("close = " + url);
//    }
//
//    // 의존관계 셋팅이 끝나면 호출
//    public void init() {
//        System.out.println("두번째 : NetworkClinet.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메세지");
//
//    }
//    // 상단이 끝나면 호출
//    public void close() {
//        System.out.println("세번째 : NetworkClinet.destroy");
//        disconnect();
//    }
//}

/*========================================================================================*/

package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 애노테이션 사용
// 최신 스프링에서 가장 권장하는 방법이다.
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("첫번째 : 클라이언트에서 호출");
        System.out.println("생성자 호출, url = " + url); // 호출
//        connect();  // 다음 연결
//        call("초기화 연결 메세지"); // 서버로 보냄
    }

    public void setUrl(String url) {
        this.url = url;

    }

    // 서비스 시작시 호출

    public void connect() {
        System.out.println("connect  = " + url);

    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message );
    }

    // 서비스 종료시 호출

    public void disconnect(){
        System.out.println("close = " + url);
    }

    // 의존관계 셋팅이 끝나면 호출
    @PostConstruct
    public void init() {
        System.out.println("두번째 : NetworkClient.init");
        connect();
        call("초기화 연결 메세지");

    }
    // 상단이 끝나면 호출
    @PreDestroy
    public void close() {
        System.out.println("세번째 : NetworkClient.close");
        disconnect();
    }
}
