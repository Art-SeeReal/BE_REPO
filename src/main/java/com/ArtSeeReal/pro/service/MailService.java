package com.ArtSeeReal.pro.service;

import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import jakarta.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MailService {

    private final String EMAIL_TITLE = "ArtSeeReal의 인증 이메일입니다.";
    private final String EMAIL_NOT_EQUAL_ERROR = "[ERROR] 이메일이 존재하지 않습니다.";
    private final String NAME_NOT_EQUAL_ERROR = "[ERROR] 이메일의 이름과 제공된 이름이 다릅니다.";
    private final String ID_NOT_EQUAL_ERROR = "[ERROR] 주어진 이메일의 아이디와 제공된 아이디가 다릅니다.";
    private final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+";
    private final JavaMailSender mailSender;
    private final Environment env;
    private final UserRepository userRepository;

    //아이디 찾기 양식
    public void findId(String name,String email) throws IOException, jakarta.mail.MessagingException {
        Optional<User> lostIdUser = userRepository.findByEmail(email);
        if(lostIdUser.isPresent() && lostIdUser.get().getName() == name){
            String setFrom = env.getProperty("auth_email"); // emailConfig에 설정한 자신의 이메일 주소를 입력
            String msgg = getTemplate("idFind"); // HTML 템플릿 파일에서 내용을 읽어옴
            msgg = msgg.replace("{userId}", lostIdUser.get().getUserId()); // {userId} 부분을 아이디로 대체
            msgg = msgg.replace("{userName}", name); // {userName} 부분을 회원 이름으로 대체
            mailSend(setFrom, email, EMAIL_TITLE, msgg);
        }else if(!lostIdUser.isPresent())
            throw new IllegalArgumentException(EMAIL_NOT_EQUAL_ERROR);
        else if(lostIdUser.get().getName() != name)
            throw new IllegalArgumentException(NAME_NOT_EQUAL_ERROR);
    }

    public void changePassword(String name,String email,String id) throws IOException, jakarta.mail.MessagingException {
        Optional<User> lostPasswordUser = userRepository.findByEmail(email);
        if(lostPasswordUser.isPresent() && lostPasswordUser.get().getName() == name && lostPasswordUser.get().getUserId() == id){
            User lostUser = lostPasswordUser.get();
            String setFrom = env.getProperty("auth_email"); // emailConfig에 설정한 자신의 이메일 주소를 입력
            String msgg = getTemplate("password"); // HTML 템플릿 파일에서 내용을 읽어옴
            String password = generateRandomString();
            lostUser.passwordChange(password);
            userRepository.save(lostUser);
            msgg = msgg.replace("{password}", password); // {userId} 부분을 아이디로 대체
            msgg = msgg.replace("{userName}", name); // {userName} 부분을 회원 이름으로 대체
            mailSend(setFrom, email, EMAIL_TITLE, msgg);
        }else if(!lostPasswordUser.isPresent())
            throw new IllegalArgumentException(EMAIL_NOT_EQUAL_ERROR);
        else if(lostPasswordUser.get().getName() != name)
            throw new IllegalArgumentException(NAME_NOT_EQUAL_ERROR);
        else if(lostPasswordUser.get().getUserId() != id)
            throw new IllegalArgumentException(ID_NOT_EQUAL_ERROR);
    }

    public String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    private String getTemplate(String type) throws IOException {
        String template = "";
        String getTemplate = env.getProperty(type + "Path.template");
        // 클래스패스(Classpath)에서 템플릿 파일을 읽어옴
        InputStream inputStream = getClass().getResourceAsStream(getTemplate);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line;
        while ((line = reader.readLine()) != null)
            template += line;
        reader.close();
        return template;
    }

    //이메일 전송 메소드
    private void mailSend(String setFrom,
                          String toMail,
                          String title,
                          String content) throws jakarta.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.

        // MimeMessageHelper 객체를 생성합니다. true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능하며, 문자 인코딩 설정도 가능합니다.
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        // 메일 발신자를 설정합니다.
        helper.setFrom(setFrom);
        // 메일 수신자를 설정합니다.
        helper.setTo(toMail);
        // 메일 제목을 설정합니다.
        helper.setSubject(title);
        // 메일 내용을 설정합니다. true를 전달하면 html 형식으로 전송되며, 작성하지 않으면 단순 텍스트로 전송됩니다.
        helper.setText(content, true);
        // 보냅니다.
        mailSender.send(message);

    }

}
