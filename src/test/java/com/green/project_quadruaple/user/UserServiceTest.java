package com.green.project_quadruaple.user;

import com.green.project_quadruaple.common.config.jwt.UserRole;
import com.green.project_quadruaple.user.mail.MailService;
import com.green.project_quadruaple.user.model.DuplicateEmailResult;
import com.green.project_quadruaple.user.model.UserSignUpReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends UserServiceParentTest{
    @Test
    @DisplayName("회원가입 성공 - 프로필 사진 있음")
    void signUser_withProfilePicture() throws Exception{
        //given
        UserSignUpReq req = new UserSignUpReq();
        req.setUserId(SIGNED_USER_ID);
        req.setEmail("test@gmail.com");
        req.setPw("password!");
        req.setName("test");

        req.getRole().add(UserRole.USER);

        MockMultipartFile file = new MockMultipartFile("pic", "profile.jpg", "image/jpeg", new byte[]{1, 2, 3});

        MailService.mailChecked.put("test@gmail.com", true);

        DuplicateEmailResult duplicateEmailResult = new DuplicateEmailResult();
        duplicateEmailResult.setUserIdCount(0);
        duplicateEmailResult.setEmailCount(0);
        given(userMapper.getEmailDuplicateInfo(req)).willReturn(duplicateEmailResult);
        given(passwordEncoder.encode(req.getPw())).willReturn("hashedPassword");
        given(myFileUtils.makeRandomFileName(file)).willReturn("randomFileName.jpg");
        given(userMapper.insUser(any())).willReturn(1);

        //when
        int result = userService.signUp(file, req);

        //then
        assertEquals(1, result);
        assertNotNull(req.getProfilePic());
        assertEquals("hashedPassword", req.getPw());

    }

}