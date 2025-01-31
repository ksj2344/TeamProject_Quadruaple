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
    UserInfo selUserInfo(long signedUserId);

    // 마이페이지 수정
    UserUpdateRes checkPassword(long signedUserId, String pw);
    void changePassword(long signedUserId, String newPw);
    int updUser(UserUpdateReq req);

    // 임시 비밀번호
    int temporaryPw(TemporaryPwDto temporaryPwDto);
    long checkUserId(String email);
    int updTemporaryPw(TemporaryPwDto temporaryPwDto);
    int changePwByTemporaryPw(TemporaryPwDto temporaryPwDto);
}
