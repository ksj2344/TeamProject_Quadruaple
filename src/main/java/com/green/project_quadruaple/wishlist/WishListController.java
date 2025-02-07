package com.green.project_quadruaple.wishlist;



import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Operation(summary = "찜 추가, 삭제")
    public ResponseEntity<ResponseWrapper<String>> toggleWishList(@RequestBody WishListReq wishListReq) {
        // 서비스 호출 후 결과 반환
        return wishlistService.toggleWishList(wishListReq.getStrfId());
    }

    @GetMapping
    @Operation(summary = "찜 목록")
    public ResponseEntity<List<WishListRes>> getWishList(@RequestParam(value = "last_index") int lastIdx) {
        List<WishListRes> wishList = wishlistService.getWishList(lastIdx);
        return ResponseEntity.ok(wishList);
    }
}


