package hello.core.scan;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class,DiscountService.class);

        DiscountService  discountService = ac.getBean(DiscountService.class);

        Member memberA = new Member(1L, "MemberA", Grade.VIP);

        int discountPrice = discountService.discount(memberA,1000,"fixDiscountPolicy");

        assertThat(discountPrice).isEqualTo(1000);

        int discountPrice1 = discountService.discount(memberA,20000,"rateDiscountPolicy");
        assertThat(discountPrice).isEqualTo(2000);
    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member memberA, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(memberA,price);
        }
    }
}
