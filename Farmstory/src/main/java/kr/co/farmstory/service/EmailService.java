package kr.co.farmstory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    // 인증번호 생성
    private final int code = ThreadLocalRandom.current().nextInt(100000, 1000000);

    @Value("${spring.mail.username}")
    private String id;

    public MimeMessage createMessage(String receiver) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, receiver); // 수신인
        message.setSubject("Farmstory 인증코드", "UTF-8"); // 메일 제목

        String content = "<h1>인증 코드는 "+code+" 입니다.</h1>";

        message.setText(content, "UTF-8", "html");
        message.setFrom(new InternetAddress(id, "Farmstory_Admin"));

        return message;
    }

    public int[] sendEmail(String receiver) {

        int status = 0;

        try{
            MimeMessage message = createMessage(receiver);
            javaMailSender.send(message); // 메일 발송
            status = 1;
        }catch(Exception e){
            e.printStackTrace();
            status = 0;
        }

        int result[] = {status, code};

        return result;
    }




}

