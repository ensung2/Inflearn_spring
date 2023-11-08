package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        // 등록
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        
        // 사용
        Integer convert = conversionService.convert("10", Integer.class);
        System.out.println("convert =" + convert);
        // 등록된 컨버터 중 사용할 수 있는 컨버터 조회하여 사용 (아래 코드는 사용한 컨버터 정보)
        // 12:06:59.838 [main] INFO hello.typeconverter.converter.StringToIntegerConverter - converter source=10
        
/*        String iTos = conversionService.convert(10, String.class);
        assertThat(iTos).isEqualTo("10");*/
        // 위 코드 한줄로 설정 (ctrl + alt + n)
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");

        IpPort result = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));

        String ipPortString = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(ipPortString).isEqualTo("127.0.0.1:8080");
    }
}
