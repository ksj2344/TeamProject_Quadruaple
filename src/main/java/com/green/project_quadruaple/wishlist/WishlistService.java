package com.green.project_quadruaple.wishlist;

import com.green.project_quadruaple.common.config.jwt.JwtTokenProvider;
import com.green.project_quadruaple.wishlist.model.WishlistMapper;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistMapper wishlistMapper;

    public void addWishList(WishListReq wishListReq) {
        wishlistMapper.insertWishList(wishListReq.getStrfId());
    }

    public List<WishListRes> getWishList(List<String> category, int page) {
        return wishlistMapper.selectWishList(category, page);
    }

}