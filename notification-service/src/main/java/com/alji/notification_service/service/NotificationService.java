package com.alji.notification_service.service;


import com.alji.notification_service.order.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;
    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Got message from order placed service {}", orderPlacedEvent);

//        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//
//            mimeMessageHelper.setFrom("no-reply@aljis.com");
//            mimeMessageHelper.setTo(orderPlacedEvent.getEmail());
//            mimeMessageHelper.setSubject(String.format("Your order with OrderNumber %s is placed successfully", orderPlacedEvent.getOrderNumber()));
//            mimeMessageHelper.setText(String.format("" +
//                    "Hi,," +
//                    "" +
//                    "Your order with orderNumber %s is placed successfully" +
//                    "" +
//                    "Best Regards," +
//                    "Spring Shop", orderPlacedEvent.getOrderNumber()));
//        };
//
//        try{
//            javaMailSender.send(mimeMessagePreparator);
//            log.info("Email sent for orderNumber {}", orderPlacedEvent.getOrderNumber());
//        } catch (MailException e) {
//            log.error("Exception occurred while sending an email", e);
//            throw new RuntimeException("Exception occurred");
//        }
    }
}
