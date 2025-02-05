package com.green.project_quadruaple.datamanager;

import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.datamanager.model.MenuReq;
import com.green.project_quadruaple.datamanager.model.ReviewDummyReq;
import com.green.project_quadruaple.datamanager.model.StrfIdGetReq;
import com.green.project_quadruaple.review.model.ReviewPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("data")
@Tag(name = "더미데이터", description = "기능용x 더미데이터이미지용")
public class DataController {
    private final DataService dataService;

    @PostMapping("strf")
    @Operation(summary = "strf사진과 메뉴넣기", description = "strfTitle: 검색할 단어, null허가, picFolder: 복사할폴더명, startId/endId: 제한할strfId범위, null허가. menus: strf에 넣을메뉴들. 3개씩")
    public ResponseEntity<?> insPicToStrf(@RequestBody StrfIdGetReq p){
        return dataService.insPicAndMenuToStrf(p);
    }

    @DeleteMapping("strf")
    @Operation(summary = "strf사진지우기", description = "카테고리, 원한다면 제목과 범위도")
    public ResponseEntity<?> delPicToStrf(@RequestBody StrfIdGetReq p){
        return dataService.delPicToStrf(p);
    }

//    @PostMapping("menu")
//    @Operation(summary = "Menu넣기", description = "post-man에서 하셔야함")
//    public ResponseEntity<?> insMenu(@RequestBody MenuReq p){
//        log.info("MenuReq:{}",p);
//        return dataService.insPicAndMenuToStrf(p);
//    }

//    @DeleteMapping("menu")
//    @Operation(summary = "menu지우기", description = "카테고리, 원한다면 제목과 범위도")
//    public ResponseEntity<?> delMenu(@RequestBody StrfIdGetReq p){
//        return dataService.delMenu(p);
//    }

    @PostMapping("review")
    public ResponseEntity<ResponseWrapper<Integer>> postreviewdummy(@RequestPart List<MultipartFile> pics, @RequestPart ReviewDummyReq p){
        return postreviewdummy(pics, p);
    }

}
