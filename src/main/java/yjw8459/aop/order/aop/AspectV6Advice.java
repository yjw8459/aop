package yjw8459.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect //Aspect 표식일 뿐, 컴포넌트 스캔 대상이 아님
/**
 * 실행 시점 별로 여러 어드바이스가 있다.
 * @Around가 가장 강력하다.
 * @Before의 경우는 JoinPoint.proceed()를 호출할 필요가 없다. 프레임워크에서 자동으로 호출.
 *
 * 호출 메서드의 Return 이후에 호출되는 어드바이스는 호출 메서드의 Return을 받을 수 있다.
 * 하지만 @Around와는 다르게 Return을 변환할 수 없다.
 */
public class AspectV6Advice {
//
//    @Around("yjw8459.aop.order.aop.PointCuts.orderAndService()")
//    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
//        try {
//            //@Before
//            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            //@AfterReturning
//            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
//            return result;
//        }
//        catch (Exception e){
//            //@AfterThrowing
//            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
//            throw e;
//        }finally {
//            //@After
//            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
//        }
//    }
//
    //간단 사용 어드바이스
    @Before("yjw8459.aop.order.aop.PointCuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint){  //JoinPoint 매개변수 필요없음.
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "yjw8459.aop.order.aop.PointCuts.orderAndService()",
            returning = "result")// returning도 정의해줘야 함.
    public void deReturn(JoinPoint joinPoint, Object result){
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterReturning(value = "yjw8459.aop.order.aop.PointCuts.allOrder()",
            returning = "result")// returning도 정의해줘야 함.
    public void deReturn2(JoinPoint joinPoint, Object result){//AfterReturning은 호출 메서드의 return을 받을 수 있다.
        log.info("[return2] {} return2={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "yjw8459.aop.order.aop.PointCuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex){
        log.info("[ex] {} message={}", ex );
    }

    @After(value = "yjw8459.aop.order.aop.PointCuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint){
        log.info("[after] {}", joinPoint.getSignature());
    }
}