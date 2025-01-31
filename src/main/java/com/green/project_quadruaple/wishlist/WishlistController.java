package com.green.project_quadruaple.wishlist;



import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@RestController
@RequestMapping("wish-list")
public class WishlistController {
    private final AuthenticationFacade authenticationFacade;

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public ResponseEntity<String> toggleWishList(@RequestBody WishListReq wishListReq) {
        long userId = AuthenticationFacade.getSignedUserId();
        String resultMessage = wishlistService.toggleWishList(userId, wishListReq);
        return ResponseEntity.ok(resultMessage);
    }

    /*// GET - 찜 목록 조회
    @GetMapping
    public ResponseEntity<List<WishListRes>> getWishList(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        long userId = AuthenticationFacade.getSignedUserId();
        List<WishListRes> wishLists = wishlistService.getWishList(userId, category, page);
        return ResponseEntity.ok(wishLists);
    }*/

    @GetMapping
    public ResponseEntity<Map<String, Object>> getWishListNew(
            @RequestParam(value = "categoryList", required = false) List<String> categoryList,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "userId") Long userId
    ) {
        Map<String, Object> response = wishlistService.getWishListWithPagingNew(userId, categoryList, page);
        return ResponseEntity.ok(response);
    }
}



