package com.green.project_quadruaple.recent;

import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.recent.model.HideRecentUpdReq;
import com.green.project_quadruaple.recent.model.HideRecentsUpdReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("recent")
@Tag(name = "최근 본 목록")
public class RecentController {
    private final RecentService recentService;

    @PatchMapping("hide")
    public ResponseEntity<?> hideRecentItem(
            HideRecentUpdReq req) {

        int updatedCount = recentService.recentHide(req);

        return ResponseEntity.ok(updatedCount);
    }
    @PatchMapping("hide/all")
    public ResponseEntity<?> recentAllHide(HideRecentsUpdReq req) {
        int updatedCount = recentService.recentAllHide(req);

        return ResponseEntity.ok(updatedCount);
    }
}
