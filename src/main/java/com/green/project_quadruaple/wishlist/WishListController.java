package com.green.project_quadruaple.wishlist;



import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListReq;
import com.green.project_quadruaple.wishlist.model.wishlistDto.WishListRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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
    @Operation(summary = "내 찜 목록 조회", description = "카테고리 리스트와 페이지를 기반으로 찜한 상품 조회")
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> getWishList(
            @RequestParam("categoryList") List<String> categoryList,
            @RequestParam("page") int page
    ) {
        ResponseWrapper<Map<String, Object>> response = wishlistService.getWishList(categoryList, page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /*@GetMapping
    public ResponseEntity<ResponseWrapper<Map<String, Object>>> getWishList(
            @RequestParam(value = "categoryList", required = false) List<String> categoryList,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        long userId = authenticationFacade.getSignedUserId();

        Map<String, Object> response = wishlistService.getWishListWithPagingNew(userId, categoryList, page);
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), response));
    }*/

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


