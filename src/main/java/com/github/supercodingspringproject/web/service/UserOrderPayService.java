package com.github.supercodingspringproject.web.service;

import com.github.supercodingspringproject.repository.generalUser.GeneralUser;
import com.github.supercodingspringproject.repository.generalUser.GeneralUserRepository;
import com.github.supercodingspringproject.repository.orders.Order;
import com.github.supercodingspringproject.repository.orders.OrderRepository;
import com.github.supercodingspringproject.repository.orders.OrderStatus;
import com.github.supercodingspringproject.repository.payment.Payment;
import com.github.supercodingspringproject.repository.payment.PaymentRepository;
import com.github.supercodingspringproject.repository.payment.PaymentType;
import com.github.supercodingspringproject.repository.sneaker.SneakerRepository;
import com.github.supercodingspringproject.service.exceptions.InvalidValueException;
import com.github.supercodingspringproject.service.exceptions.NotAcceptException;
import com.github.supercodingspringproject.service.exceptions.NotFoundException;
import com.github.supercodingspringproject.service.mapper.OrderMapper;
import com.github.supercodingspringproject.web.dto.OrderInfo;
import com.github.supercodingspringproject.web.dto.PayRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserOrderPayService {

    private final GeneralUserRepository generalUserRepository;
    private final SneakerRepository sneakerRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public Page<OrderInfo> listOrderInfo(Integer generalUserID, Pageable pageable) {
        Page<Order> orders = orderRepository.findAllByGeneralUserId(generalUserID, pageable);
        return orders.map(OrderMapper.INSTANCE::orderToOrderInfo);
    }

    @Transactional(transactionManager = "tmJpa")
    public Boolean makePay(PayRequest payRequest) {
        Integer generalUserId = payRequest.getGeneralUserId();
        Integer orderId = payRequest.getOrderId();

        PaymentType paymentType = PaymentType.valueOfTerm(payRequest.getType());

        GeneralUser generalUser = generalUserRepository.findById(generalUserId).orElseThrow(() -> new NotFoundException("gnenerUser '" + generalUserId + "' 를  찾을 수 없습니다."));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("order '" + orderId + "' 를 찾을 수 없습니다."));

        if ( order.getOrderStatus() != OrderStatus.ORDER_COMPLETED ) throw new NotAcceptException("해당 주문은 '주문완료' 상태가 아니어서 결제할 수 없습니다.");
        if ( paymentType == PaymentType.CARD && generalUser.getMyCardNum() == null ) throw new NotAcceptException("카드 결제의 경우, 카드가 등록되어있어야합니다.");
        if ( paymentType == PaymentType.ACCOUNT && generalUser.getMyBankAccount()  == null ) throw new NotAcceptException("계좌 이체 결제의 경우, 계좌가 등록되어있어야합니다.");

        Payment payment = Payment.builder()
                .gUser(generalUser)
                .order(order)
                .type(paymentType)
                .paymentAt(LocalDateTime.now())
                                 .build();

        Payment paymentSaved = paymentRepository.save(payment);

        paymentSaved.getOrder().setOrderStatus(OrderStatus.PAY_COMPLETED);

        return paymentSaved.getId() != null;
    }
}
