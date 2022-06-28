package yjw8459.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect //Aspect 표식일 뿐, 컴포넌트 스캔 대상이 아님
/**
 * Order로 순서를 적용할 수 있으나, Aspect 단위로 설정 가능
 */
public class AspectV5Order {

    @Aspect
    @Order(2)
    public static class LogAspect{
        @Around("yjw8459.aop.order.aop.PointCuts.allOrder()")    //allOrder의 포인트컷 사용
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[Log] {}", joinPoint.getSignature()); //어드바이스(부가기능) joinPoint 시그니처 : 메서드 정보
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect{
        //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
        @Around("yjw8459.aop.order.aop.PointCuts.orderAndService()")   // 여러 개의 포인트 컷 조합(AND, OR, NOT)
        public Object odTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            }
            catch (Exception e){
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            }finally {
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }
    }
}