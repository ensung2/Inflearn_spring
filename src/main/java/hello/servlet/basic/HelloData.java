package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;


// lombok 라이브러리 사용으로 하단 주석처리된 코드가 자동으로 생성됨(수동 작성할 필요 없음)
@Getter
@Setter
public class HelloData {

    private String username;
    private int age;

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

}
