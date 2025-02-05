package com.green.project_quadruaple.wishlist;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WishListService {
    private final AuthenticationFacade authenticationFacade;
    private final WishListMapper wishlistMapper;

    public WishListService(AuthenticationFacade authenticationFacade, WishListMapper wishListMapper) {
        this.authenticationFacade = authenticationFacade;
        this.wishlistMapper = wishListMapper;
    }

    public ResponseEntity<ResponseWrapper<String>> toggleWishList(long strfId) {
        long userId = authenticationFacade.getSignedUserId();

        // 찜 상태 확인
        boolean isWishListed = wishlistMapper.isWishListExists(userId, strfId);

        if (isWishListed) {
            // 찜 삭제
            wishlistMapper.deleteWishList(userId, strfId);
            return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), "찜이 삭제되었습니다."));
        } else {
            // 찜 추가
            wishlistMapper.insertWishList(userId, strfId);
            return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), "찜이 추가되었습니다."));
        }
    }

    public ResponseWrapper<Map<String, Object>> getWishList(List<String> categoryList, int page) {
        int limit = 10;
        int offset = (page - 1) * limit;

        List<Map<String, Object>> wishList = wishlistMapper.getWishList(categoryList, offset, limit);
        return new ResponseWrapper<>(ResponseCode.OK.getCode(), Map.of("wishList", wishList));
    }


    }

    /*public Map<String, Object> getWishListWithPagingNew(long userId, List<String> categoryList, int page) {
        int offset = (page - 1) * 10; // 페이징 offset 계산

        // DB에서 찜 목록 조회
        List<WishListRes> wishLists = wishlistMapper.findWishList(userId, categoryList, offset);

        // 응답 데이터 구성
        Map<String, Object> result = new HashMap<>();
        result.put("isMore", wishLists.size() > 10); // 10개 이상일 경우 추가 페이지 존재
        result.put("wishList", wishLists.size() > 10 ? wishLists.subList(0, 10) : wishLists);

        return result;
    }*/



