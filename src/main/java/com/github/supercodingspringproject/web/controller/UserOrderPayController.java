package com.github.supercodingspringproject.web.controller;


import com.github.supercodingspringproject.web.dto.OrderInfo;
import com.github.supercodingspringproject.web.dto.PayRequest;
import com.github.supercodingspringproject.web.service.UserOrderPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/user-order-pay")
@Slf4j
public class UserOrderPayController {

    private final UserOrderPayService userOrderPayService;

    @GetMapping("/orders")
    public Page<OrderInfo> listOrderInfo(@RequestParam("g-user-id") Integer generalUserId, Pageable pageable){
        return userOrderPayService.listOrderInfo(generalUserId, pageable);
    }

    @PostMapping("/pays")
    public String makePayment(@RequestBody PayRequest payRequest){
        Boolean isSuccess = userOrderPayService.makePay(payRequest);
        return isSuccess ? "결제 성공하였습니다." : "결제 실패하였습니다.";
    }
}
