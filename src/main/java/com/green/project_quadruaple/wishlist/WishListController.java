package com.green.project_quadruaple.wishlist;



import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("wish-list")
public class WishListController {

    private final WishListService wishlistService;


    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> toggleWishList(@RequestBody WishListReq wishListReq) {
        // 서비스 호출 후 결과 반환
        return wishlistService.toggleWishList(wishListReq.getStrfId());
    }

    @GetMapping
    public ResponseEntity<List<WishListRes>> getWishList(
            @RequestParam("userId") Long userId,
            @RequestParam(value = "limit", defaultValue = "11") int limit) {
        List<WishListRes> wishList = wishlistService.getWishList(userId, limit);
        return ResponseEntity.ok(wishList);
    }
}


