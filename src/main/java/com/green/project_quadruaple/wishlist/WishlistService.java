package com.green.project_quadruaple.wishlist;

import com.green.project_quadruaple.wishlist.model.WishlistMapper;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WishlistService {

    private final WishlistMapper wishlistMapper;

    public WishlistService(WishlistMapper wishlistMapper) {
        this.wishlistMapper = wishlistMapper;
    }

    public String toggleWishList(long userId, WishListReq wishListReq) {
        // 찜 목록에 이미 존재하는지 확인
        boolean exists = wishlistMapper.isWishListExists(userId, wishListReq.getStrfId());

        if (exists) {
            // 이미 존재하면 삭제
            wishlistMapper.deleteWishList(userId, wishListReq.getStrfId());
            return "찜 목록에서 삭제되었습니다.";
        } else {
            // 존재하지 않으면 추가
            wishListReq.setUserId(userId);
            wishlistMapper.insertWishList(wishListReq);
            return "찜 목록에 추가되었습니다.";
        }

    }

    public Map<String, Object> getWishListWithPagingNew(Long userId, List<String> categoryList, int page) {
        int offset = (page - 1) * 10; // Offset 계산
        List<WishListRes> wishLists = wishlistMapper.findWishList(userId, categoryList, offset);

        Map<String, Object> result = new HashMap<>();
        result.put("isMore", wishLists.size() > 10); // 다음 페이지 확인
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