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
    // GET 요청을 처리할 getWishList 메서드 추가
    /*public Map<String, Object> getWishListWithPaging(Long userId, List<String> categoryList, int page) {
        int offset = (page - 1) * 10; // 페이징 계산
        List<WishListRes> wishLists = wishlistMapper.findWishList(userId, categoryList, offset);

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("isMore", wishLists.size() >= 10); // 10개 이상이면 다음 페이지 있음
        result.put("wishList", wishLists);

        return result;
    }*/
    public Map<String, Object> getWishListWithPagingNew(Long userId, List<String> categoryList, int page) {
        int offset = (page - 1) * 10;

        List<WishListRes> wishLists = wishlistMapper.findWishList(userId, categoryList, offset);

        Map<String, Object> result = new HashMap<>();
        result.put("isMore", wishLists.size() > 10); // 다음 페이지 여부 확인
        result.put("wishList", wishLists);

        return result;
    }


}