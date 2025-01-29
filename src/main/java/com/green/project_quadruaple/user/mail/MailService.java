package com.green.project_quadruaple.user.mail;

import com.green.project_quadruaple.common.model.ResultResponse;
import com.green.project_quadruaple.user.mail.thread.AuthCode;
import com.green.project_quadruaple.user.mail.thread.MailCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    @Autowired
    private final JavaMailSender mailSender;

    public static Map<String, String> codes = new HashMap<>();
    public static Map<String, Boolean> mailChecked = new HashMap<>();

    @Autowired
    private ResourceLoader resourceLoader; // ResourceLoader 추가

    public ResultResponse send(String email) {
        // 인증코드 생성
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            code.append((int)(Math.random() * 10));
        }
        MailService.codes.put(email, String.valueOf(code));

        // 인증코드 유지 시간 설정
        new Thread(new AuthCode(email)).start();

        try {
            // HTML 템플릿 로드
            Resource resource = resourceLoader.getResource("classpath:/templates/email_template.html");
            String template = new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);

            // 인증 코드 삽입 (템플릿에 %s가 있어야 합니다)
            String body = template.replace("%s", codes.get(email));

            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setFrom("quadrupleart@gmail.com", "quadruple");
            mailHandler.setTo(email);
            mailHandler.setSubject("quadruple 인증 코드");
            mailHandler.setText(body, true);  // HTML 형식으로 전송
            mailHandler.send();

        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.severError();
        }
        return ResultResponse.success();
    }

    public ResultResponse check(GetEmailAndCodeReq req) {
        String email = req.getEmail();
        String code = req.getCode();
        String savedCode = codes.getOrDefault(email, "");
        if (!savedCode.equals(code)) {
            return new ResultResponse("FAIL");
        }
        codes.remove(email);
        mailChecked.put(email, true);
        new Thread(new MailCheck(email)).start(); // 3분간 인증 성공, 이후 만료
        return ResultResponse.success();
    }
}