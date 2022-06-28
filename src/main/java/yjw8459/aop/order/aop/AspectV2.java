package yjw8459.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect //Aspect 표식일 뿐, 컴포넌트 스캔 대상이 아님
/**
 * 스프링 빈으로 등록하는 세가지 방법
 * @Bean        : 직접 등록
 * @Component   : 컴포넌트 스캔을 이용한 자동 등록
 * @Import      : 설정 파일을 추가할 때 사용(@Configuration)
 */
public class AspectV2 {

    // Pointcut 분리 hello.aop.order 패키지와 하위 패키지
    // Pointcut을 분리함으로써 의미부여 가능, 하나의 Pointcut을 여러 곳에서 사용할 수 있음
    @Pointcut("execution(* yjw8459.aop.order..*(..))") 
    private void allOrder(){}   //pointcut signature

    @Around("allOrder()")    //allOrder의 포인트컷 사용
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("[Log] {}", joinPoint.getSignature()); //어드바이스(부가기능) joinPoint 시그니처 : 메서드 정보
        return joinPoint.proceed();
    }

    @Around("allOrder()")    //allOrder의 포인트컷 사용
    public Object doLog2(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("[Log] {}", joinPoint.getSignature()); //어드바이스(부가기능) joinPoint 시그니처 : 메서드 정보
        return joinPoint.proceed();
    }
}
