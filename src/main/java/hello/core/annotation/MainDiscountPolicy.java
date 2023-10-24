package hello.core.annotation;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;


// annotation 직접 만들기
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")    //이렇게 적어주면 컴파일시 타입 체크가 안된다
public @interface MainDiscountPolicy {

}
