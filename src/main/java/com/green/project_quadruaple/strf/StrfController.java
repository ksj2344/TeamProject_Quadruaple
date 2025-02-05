package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.strf.model.GetNonDetail;
import com.green.project_quadruaple.strf.model.StrfSelRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("detail")
@RequiredArgsConstructor
@Tag(name = "상품")
public class StrfController {
    private final StrfService strfService;

    @GetMapping("member")
    @Operation(summary = "회원 상품 조회")
    public ResponseWrapper<StrfSelRes> getMemberDetail(@RequestParam("strf_id") Long strfId) {
        return strfService.getMemberDetail(strfId);
    }

    @GetMapping("member/non")
    @Operation(summary = "비회원 상품 조회")
    public ResponseWrapper<GetNonDetail> getNonMemberDetail (@RequestParam("strf_id") Long strfId){
        return strfService.getNonMemberDetail(strfId);
    }


}
