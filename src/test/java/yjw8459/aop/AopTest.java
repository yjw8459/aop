package yjw8459.aop;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import yjw8459.aop.order.OrderRepository;
import yjw8459.aop.order.OrderService;
import yjw8459.aop.order.aop.*;

@Slf4j
@SpringBootTest
/**
 * @Import : 스프링 빈으로 등록
 */
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@Import(AspectV6Advice.class)
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo(){
        //AopUtils.isAopProxy AopProxy가 적용이 되었는지
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success(){
        orderService.orderItem("itemA");
    }

    @Test
    void exception(){
        Assertions.assertThatThrownBy( () -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}
