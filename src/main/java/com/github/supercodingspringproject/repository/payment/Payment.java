package com.github.supercodingspringproject.repository.payment;

import com.github.supercodingspringproject.repository.generalUser.GeneralUser;
import com.github.supercodingspringproject.repository.orders.Order;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "g_user_id", nullable = false)
    private GeneralUser gUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PaymentType type;

    @Column(name = "payment_at", nullable = false)
    private LocalDateTime paymentAt;

}