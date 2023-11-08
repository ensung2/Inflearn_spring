package hello.typeconverter;

import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//스프링은 내부에서 ConversionService 를 제공
// WebMvcConfigurer가 제공하는 addFormatters() 를 사용해서 추가하고 싶은 컨버터를 등록하면 된다.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 등록해 주지 않아도 컨버터는 잘 돌아가지만 등록해주면 우선순위를 갖게된다.
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new IpPortToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
//        registry.addConverter(new StringToIntegerConverter());    => 우선순위를 위해 주석처리해둠
//        registry.addConverter(new IntegerToStringConverter());

        // 추가
        registry.addFormatter(new MyNumberFormatter());
    }
}
