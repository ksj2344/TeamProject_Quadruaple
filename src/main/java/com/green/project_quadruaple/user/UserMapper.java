package com.green.project_quadruaple.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    // 이메일 중복 체크
    boolean checkEmail(DuplicateEmailDao email);
    // 회원가입
    int insUser(UserSignUpReq req);
    int insUserRole(UserSignUpReq req);
    int updUser(String email);

    // 로그인
    Optional<UserSelOne> selUserByEmail(UserSignInReq req);
}
