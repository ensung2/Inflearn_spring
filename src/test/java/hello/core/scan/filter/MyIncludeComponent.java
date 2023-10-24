package hello.core.scan.filter;

import java.lang.annotation.*;

// @Component에서 가져옴
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

// 해당 애노테이션이 붙으면 컴포넌트에서 포함
public @interface MyIncludeComponent {
}
