package com.green.project_quadruaple.picmanager;

import com.green.project_quadruaple.picmanager.model.StrfPicReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "사용자", description = "기능용x 더미데이터이미지용")
public class PicController {
    private final PicService picService;

    @PostMapping
    @Operation(summary = "strf사진넣기", description = "어디사이에 strfId에 사진넣을지")
    public ResponseEntity<?> insPicToStrf(@RequestPart List<MultipartFile> pics, @RequestPart StrfPicReq p){
        return picService.insPicToStrf(pics, p);
    }

}
