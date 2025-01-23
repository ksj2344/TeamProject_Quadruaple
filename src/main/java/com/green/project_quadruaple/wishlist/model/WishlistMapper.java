package com.green.project_quadruaple.wishlist.model;

import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WishlistMapper {
    void insertWishList(@Param("strfId") long strfId);

    List<WishListRes> selectWishList(@Param("category") List<String> category, @Param("page") int page);



}