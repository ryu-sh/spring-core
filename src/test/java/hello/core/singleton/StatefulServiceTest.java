package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    @DisplayName("")
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean1 = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        bean1.order("nameA",10000);
        //ThreadB : B사용자 20000원 주문
        bean1.order("nameB",20000);

        //ThreadA : 사용자A 주문 금액 조회
        int price = bean1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(bean1).isSameAs(bean2);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
