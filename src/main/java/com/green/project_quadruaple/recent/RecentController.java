package com.green.project_quadruaple.recent;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("recent")
@Tag(name = "최근 본 목록")
public class RecentController {
    private final RecentService recentService;

    @PatchMapping("hide")
    public ResponseEntity<?> hideRecentItem(
            @RequestParam("strf_id") Long strfId) {

        int updatedCount = recentService.recentHide(strfId);

        return ResponseEntity.ok(updatedCount);
    }
    @PatchMapping("hide/all")
    public ResponseEntity<?> recentAllHide() {
        int updatedCount = recentService.recentAllHide();

        return ResponseEntity.ok(updatedCount);
    }
}
