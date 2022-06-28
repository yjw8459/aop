package yjw8459.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect //Aspect 표식일 뿐, 컴포넌트 스캔 대상이 아님
/**
 * 스프링 빈으로 등록하는 세가지 방법
 * @Bean        : 직접 등록
 * @Component   : 컴포넌트 스캔을 이용한 자동 등록
 * @Import      : 설정 파일을 추가할 때 사용(@Configuration)
 */
public class AspectV1 {

    @Around("execution(* yjw8459.aop.order..*(..))")    //포인트 컷
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("[Log] {}", joinPoint.getSignature()); //어드바이스(부가기능) joinPoint 시그니처 : 메서드 정보
        return joinPoint.proceed();
    }
}
