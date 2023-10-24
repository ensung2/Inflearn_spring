/* 해당 파일을 최상단에 둘 경우 하단 까지 다 탐색해주기 때문에 권장 */

package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 탐색할 패키지의 시작 위치를 지정한다(이 패키지를 포함한 하위페이지 모두 탐색 / 탐색시간 단축)
        basePackages = "hello.core.member",

        // 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
        // 만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        basePackageClasses = AutoAppConfig.class,

        // 컴포넌트 애노테이션이 붙은 클래스를 스캔해서 스피링빈으로 등록한다
        // 해당 코드는 스캔으로 확인한 다음 제외할 항목을 작성
        // appconfig는 수동으로 입력한 부분이므로 충돌이 일어날 가능성이 있으므로 제외
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

)
public class AutoAppConfig {


}
