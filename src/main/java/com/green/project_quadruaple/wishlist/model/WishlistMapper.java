package com.green.project_quadruaple.wishlist.model;

import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListGetReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WishlistMapper {

    void insertWishList(WishListReq wishListReq);
    List<WishListRes> findWishList(WishListGetReq req);
    void deleteWishList(@Param("userId") long userId, @Param("strfId") long strfId);

    boolean isWishListExists(@Param("userId") long userId, @Param("strfId") long strfId);

    List<WishListRes> findWishList(
            Long userId,
            List<String> categoryList,
            int offset
    );
}
