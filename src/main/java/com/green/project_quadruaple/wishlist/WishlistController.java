package com.green.project_quadruaple.wishlist;



import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRes;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class WishlistController {

    private final WishlistService wishListService;

    @Operation(summary = "위시리스트 추가", description = "위시리스트에 항목을 추가합니다.")
    @PostMapping("/wish-list")
    public ResponseEntity<String> postWishList(@RequestBody WishListReq wishListReq) {
        wishListService.addWishList(wishListReq);
        return ResponseEntity.ok("Wishlist added successfully");
    }
    @Operation(summary = "위시리스트 조회", description = "사용자의 위시리스트를 조회합니다.")
    @GetMapping("/wish-list")
    public ResponseEntity<List<WishListRes>> getWishList(
            @RequestParam(value = "category", required = false) List<String> category,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        List<WishListRes> wishList = wishListService.getWishList(category, page);
        return ResponseEntity.ok(wishList);
    }
}


