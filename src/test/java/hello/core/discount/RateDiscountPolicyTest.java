package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 적용")
    void vip_o(){
        //given
        Member member = new Member(1L, "member", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 1000);
        //then
        org.assertj.core.api.Assertions.assertThat(discount).isEqualTo(100);
    }

    @Test
    @DisplayName("VIP 아니면 적용 x")
    void vip_x(){
        //given
        Member member = new Member(1L, "member", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 1000);
        //then
        org.assertj.core.api.Assertions.assertThat(discount).isEqualTo(100);
    }

}