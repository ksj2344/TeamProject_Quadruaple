package com.green.project_quadruaple.wishlist.model.wishlistDto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListReq {
    private long userId;     // 사용자 ID (자동 설정됨)
    private long strfId;     // 찜할 항목의 ID


}