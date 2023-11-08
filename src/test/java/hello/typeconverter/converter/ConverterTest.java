package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");   // 문자 10을 int 10으로 컨버터
        assertThat(result).isEqualTo(10);   // 결과가 숫자 10과 같아야 함
    }

    @Test
    void IntegerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);  // 숫자 10을 str 10으로 컨버터
        assertThat(result).isEqualTo("10"); // 결과가 문자 10과 같아야 함
    }

    @Test
    void stringToIpPort() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);   // 테스트 소스 넣어주기
        String result = converter.convert(source);

        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void ipPortToString() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);

        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }


}
