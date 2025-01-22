package com.green.project_quadruaple.user;

import com.green.project_quadruaple.user.model.DuplicateEmailResult;
import com.green.project_quadruaple.user.model.UserSelOne;
import com.green.project_quadruaple.user.model.UserSignInReq;
import com.green.project_quadruaple.user.model.UserSignUpReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    // 이메일 중복 체크
    DuplicateEmailResult checkEmail(UserSignUpReq req);
    // 회원가입
    int insUser(UserSignUpReq req);
    int insUserRole(UserSignUpReq req);
    boolean checkEmail(String email);

    // 로그인
    Optional<UserSelOne> selUserByEmail(UserSignInReq req);
}
