package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
                                                            // 파라미터, 반환값
public class IntegerToStringConverter implements Converter<Integer, String> {

    // integer를 string로 변경
    @Override
    public String convert(Integer source) {
        log.info("converter source={}", source);
        return String.valueOf(source);
    }
}
