package com.ArtSeeReal.pro.serviceImpl;

import com.ArtSeeReal.pro.entity.history.UserHistory;
import com.ArtSeeReal.pro.entity.main.User;
import com.ArtSeeReal.pro.enums.system.SystemStringEnum;
import com.ArtSeeReal.pro.repository.jpa.history.UserHistoryRepository;
import com.ArtSeeReal.pro.repository.jpa.main.UserRepository;
import com.ArtSeeReal.pro.repository.memory.MemoryRepository;
import com.ArtSeeReal.pro.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import static com.ArtSeeReal.pro.enums.error.ErrorCode.*;
import static com.ArtSeeReal.pro.enums.system.SystemConstantEnum.ID_MINIMUM_LENGTH;
import static com.ArtSeeReal.pro.enums.system.SystemConstantEnum.ID_REFERENCE_POINT;
import static com.ArtSeeReal.pro.enums.system.SystemStringEnum.EMAIL_TITLE;
import static com.ArtSeeReal.pro.enums.system.SystemStringEnum.MASKED_CHARACTERS;
import static com.ArtSeeReal.pro.etc.Uid.uidCreator;

@Service
@RequiredArgsConstructor
@Transactional
@EnableScheduling
@Log4j2
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final Environment env;
    private final UserRepository userRepository;
    private final MemoryRepository memoryRepository;
    private final UserHistoryRepository userHistoryRepository;

    //아이디 찾기 양식
    public String findId(String name, String email) {
        User lostIdUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(EMAIL_NOT_EQUAL_ERROR.getMessage()));

        if (!lostIdUser.getName().equals(name))
            throw new IllegalArgumentException(NAME_NOT_EQUAL_ERROR.getMessage());

        String userId = lostIdUser.getUserId();
        int idLength = userId.length();

        if (idLength >= ID_REFERENCE_POINT.getNumber())
            return userId.substring(0,idLength - 3) + MASKED_CHARACTERS.getText().repeat(3);
        else if (idLength >= ID_MINIMUM_LENGTH.getNumber())
            return userId.substring(0,idLength - 2) + MASKED_CHARACTERS.getText().repeat(2);
        else
            return MASKED_CHARACTERS.getText().repeat(3);
    }

    @Override
    public void authForPassword(String name, String email, String id) throws IOException, MessagingException {
        User pwLostUser = userRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException(ID_NOT_FOUND.getMessage()));
        if(pwLostUser.getName().equals(name) && pwLostUser.getEmail().equals(email)){
            String setFrom = env.getProperty("auth_email");
            String msgg = getTemplate("password");
            msgg = msgg.replace("{name}", name);
            String authStr = memoryRepository.saveAuthStr(pwLostUser.getUid(), SystemStringEnum.generateRandomString());
            msgg = msgg.replace("{authStr}", authStr);
            mailSend(setFrom, email, EMAIL_TITLE.getText(), msgg);
        } else if (!pwLostUser.getName().equals(name)) {
            throw new IllegalArgumentException(NAME_NOT_EQUAL_ERROR.getMessage());
        } else if (!pwLostUser.getEmail().equals(email)) {
            throw new IllegalArgumentException(EMAIL_NOT_EQUAL_ERROR.getMessage());
        }
    }

    @Override
    public String authCreateToken(String authRandomStr) {
        if(memoryRepository.existAuthStr(authRandomStr)) {
            // TODO : 2차적으로는 JWT 토큰으로 수정 해도 될듯
            String userUid = memoryRepository.findByUserUidFromAuthNumbers(authRandomStr);
            memoryRepository.deleteAuthStr(authRandomStr);
            return memoryRepository.saveToken(userUid, UUID.randomUUID().toString());
        }else
            throw new IllegalArgumentException(NO_AUTH_STR_ERROR.getMessage());
    }

    @Override
    public void changePassword(String token, String password) {
        if (memoryRepository.existToken(token)){
            User user = userRepository.findById(memoryRepository.findByUserUidFromTokens(token))
                    .orElseThrow(() -> new IllegalArgumentException(NO_DATA_ERROR.getMessage()));
            UserHistory userHistory = user.passwordOfUserHistory(uidCreator(userHistoryRepository), password);
            // TODO : 스프링 시큐리티 적용 후 비밀번호 암호화 요망
            user.passwordChange(password);
            userHistoryRepository.save(userHistory);
            userRepository.save(user);
            memoryRepository.deleteToken(token);
        }else
            throw new IllegalArgumentException(NO_TOKEN_ERROR.getMessage());
    }

    @Override
    @Scheduled(fixedRate = 300000)
    public void removeExpired() {
        memoryRepository.removeExpiredAuthNumbers();
        memoryRepository.removeExpiredTokens();
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
