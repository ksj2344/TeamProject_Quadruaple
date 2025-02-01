package com.green.project_quadruaple.datamanager;

import com.green.project_quadruaple.datamanager.model.MenuReq;
import com.green.project_quadruaple.datamanager.model.StrfIdGetReq;
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
    @Operation(summary = "strf사진넣기", description = "post-man에서 하셔야함")
    public ResponseEntity<?> insPicToStrf(@RequestPart List<MultipartFile> pics, @RequestPart StrfIdGetReq p){
        return dataService.insPicToStrf(pics, p);
    }

    @DeleteMapping("strf")
    @Operation(summary = "strf사진지우기", description = "카테고리, 원한다면 제목과 범위도")
    public ResponseEntity<?> delPicToStrf(@RequestBody StrfIdGetReq p){
        return dataService.delPicToStrf(p);
    }

    @PostMapping("menu")
    @Operation(summary = "Menu넣기", description = "post-man에서 하셔야함")
    public ResponseEntity<?> insMenu(@RequestPart List<MultipartFile> pics, @RequestPart MenuReq p){
        log.info("MenuReq:{}",p);
        return dataService.insPicToStrf(pics, p);
    }

    @DeleteMapping("menu")
    @Operation(summary = "menu지우기", description = "카테고리, 원한다면 제목과 범위도")
    public ResponseEntity<?> delMenu(@RequestBody StrfIdGetReq p){
        return dataService.delMenu(p);
    }

}
