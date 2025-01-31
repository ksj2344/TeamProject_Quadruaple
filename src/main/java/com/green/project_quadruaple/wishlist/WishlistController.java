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

    @PostMapping
    public ResponseEntity<String> toggleWishList(@RequestBody WishListReq wishListReq) {
        long userId = AuthenticationFacade.getSignedUserId();
        String resultMessage = wishlistService.toggleWishList(userId, wishListReq);
        return ResponseEntity.ok(resultMessage);
    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> getWishListNew(
            @RequestParam(value = "categoryList", required = false) List<String> categoryList,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "userId") Long userId
    ) {
        Map<String, Object> response = wishlistService.getWishListWithPagingNew(userId, categoryList, page);
        return ResponseEntity.ok(response);
    }

   /* // 기존
    @GetMapping
    public ResponseEntity<Map<String, Object>> getWishListNew(
            @RequestParam(value = "categoryList", required = false) List<String> categoryList,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "userId") Long userId
    ) {
        Map<String, Object> response = wishlistService.getWishListWithPagingNew(userId, categoryList, page);
        return ResponseEntity.ok(response);
    }*/

    // 페이징 처리된 위시리스트 가져오기
    /*@GetMapping
    public ResponseEntity<Map<String, Object>> getWishListNew(
            @RequestParam(value = "categoryList", required = false) List<String> categoryList,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        // 인증된 사용자 ID 가져오기
        Long userId = authenticationFacade.getSignedUserId();

        // 위시리스트 서비스 호출
        Map<String, Object> response = wishlistService.getWishListWithPaging(userId, categoryList, page);

        return ResponseEntity.ok(response);
    }*/
}


