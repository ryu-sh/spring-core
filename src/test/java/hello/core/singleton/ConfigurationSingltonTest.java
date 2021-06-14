package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingltonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService",MemberServiceImpl.class);
        OrderServiceImpl orderservice = ac.getBean("orderService", OrderServiceImpl.class);
        Object memberReposiroty = ac.getBean("memberReposiroty", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.memberRepository();
        MemberRepository memberRepository2 = orderservice.getMemberRepository();

        assertThat(memberRepository1).isSameAs(memberRepository2);
        assertThat(memberRepository1).isSameAs(memberReposiroty);

    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean);
    }
}
