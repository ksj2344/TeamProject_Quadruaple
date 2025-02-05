package com.green.project_quadruaple.wishlist;

import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListGetReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
public interface WishListMapper {

    boolean isWishListExists(@Param("userId") long userId, @Param("strfId") long strfId);
    void insertWishList(WishListReq wishListReq);
    void deleteWishList(@Param("userId") long userId, @Param("strfId") long strfId);


    void insertWishList(long userId, long strfId);
    List<WishListRes> findWishList(
            @Param("userId") long userId,
            @Param("categoryList") List<String> categoryList,
            @Param("offset") int offset
    );


    List<Map<String, Object>> getWishList(@Param("categoryList") List<String> categoryList,
                                          @Param("offset") int offset,
                                          @Param("limit") int limit);

    int getWishListCount(@Param("categoryList") List<String> categoryList);



    // 위시리스트 조회 쿼리
    /*List<WishListRes> findWishList(@Param("userId") Long userId,
                                   @Param("categoryList") List<String> categoryList,
                                   @Param("offset") int offset,
                                   @Param("pageSize") int pageSize);*/
}
