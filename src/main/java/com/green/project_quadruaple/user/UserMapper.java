package com.green.project_quadruaple.user;

import com.green.project_quadruaple.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    // 이메일 중복 체크
    boolean isEmailDuplicated(String email);
    DuplicateEmailResult getEmailDuplicateInfo(UserSignUpReq req);

    // 회원가입
    int insUser(UserSignUpReq req);
    int insUserRole(UserSignUpReq req);

    // 로그인
    Optional<UserSelOne> selUserByEmail(UserSignInReq req);

    // 마이페이지 조회
    UserInfo selUserInfo(long userId);

    // 마이페이지 수정
    UserUpdateRes checkPassword(long userId);
    void changePassword(String email, String pw);
    int updUser(UserUpdateReq req);
}
