package com.green.project_quadruaple.wishlist;

import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WishListService {
    private final AuthenticationFacade authenticationFacade;
    private final WishListMapper wishlistMapper;

    /*public String toggleWishList( long strfId) {
        long userId=authenticationFacade.getSignedUserId();
        boolean isWishListed = wishlistMapper.isWishListExists(userId, strfId);

        if (isWishListed) {
            wishlistMapper.deleteWishList(userId, strfId);
            return "찜이 삭제되었습니다.";
        } else {
            WishListReq wishListReq = new WishListReq();
            wishListReq.setStrfId(strfId);


            wishlistMapper.insertWishList(wishListReq);
            return "찜이 추가되었습니다.";
        }
    }*/
    public WishListService(AuthenticationFacade authenticationFacade, WishListMapper wishListMapper) {
        this.authenticationFacade = authenticationFacade;
        this.wishlistMapper = wishListMapper;
    }

    public String toggleWishList(long strfId) {
        // 사용자 ID를 AuthenticationFacade로 가져오기
        long userId = authenticationFacade.getSignedUserId();

        // 위시리스트 존재 여부 확인
        boolean isWishListed = wishlistMapper.isWishListExists(userId, strfId);

        if (isWishListed) {
            wishlistMapper.deleteWishList(userId, strfId);
            return "찜이 삭제되었습니다.";
        } else {
            wishlistMapper.insertWishList(userId, strfId);
            return "찜이 추가되었습니다.";
        }
    }

    public Map<String, Object> getWishListWithPagingNew(long userId, List<String> categoryList, int page) {
        int offset = (page - 1) * 10; // 페이지네이션 offset 계산

        List<WishListRes> wishLists = wishlistMapper.findWishList(userId, categoryList, offset);

        // 응답 데이터 구성
        Map<String, Object> result = new HashMap<>();
        result.put("isMore", wishLists.size() > 10); // 다음 페이지 여부 확인
        result.put("wishList", wishLists);

        return result;
    }

    /*public Map<String, Object> getWishListWithPagingNew(Long userId, List<String> categoryList, int page) {
        int offset = (page - 1) * 10;

        List<WishListRes> wishLists = wishlistMapper.findWishList(userId, categoryList, offset);

        Map<String, Object> result = new HashMap<>();
        result.put("isMore", wishLists.size() > 10); // 다음 페이지 여부 확인
        result.put("wishList", wishLists);

        return result;
    }*/
    /*// 페이징 처리가 포함된 위시리스트 가져오기
    public Map<String, Object> getWishListWithPaging(Long userId, List<String> categoryList, int page) {
        int pageSize = 10; // 기본 페이지 사이즈 설정
        int offset = (page - 1) * pageSize; // 페이징 계산

        // 위시리스트 데이터 가져오기
        List<WishListRes> wishLists = wishlistMapper.findWishList(userId, categoryList, offset, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("isMore", wishLists.size() == pageSize); // 다음 페이지 존재 여부 확인
        result.put("wishlist", wishLists); // 현재 페이지의 데이터

        return result;
    }*/
    }
