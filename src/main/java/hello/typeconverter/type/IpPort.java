package hello.typeconverter.type;


import lombok.EqualsAndHashCode;
import lombok.Getter;

// "127.0.0.1:8080" 이라는 문자가 들어왔을 떄 port 번호로 컨버터하기
@Getter
@EqualsAndHashCode
public class IpPort {

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
