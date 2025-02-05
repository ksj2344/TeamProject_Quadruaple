package com.green.project_quadruaple.datamanager;


import com.green.project_quadruaple.common.MyFileUtils;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.datamanager.model.MenuDto;
import com.green.project_quadruaple.datamanager.model.ReviewDummyReq;
import com.green.project_quadruaple.datamanager.model.StrfIdGetReq;
import com.green.project_quadruaple.datamanager.model.UserProfile;
import com.green.project_quadruaple.review.ReviewService;
import com.green.project_quadruaple.review.model.ReviewPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class DataService {
    private final DataMapper dataMapper;
    private final MyFileUtils myFileUtils;
    private final ReviewService reviewService;

    public ResponseEntity<ResponseWrapper<Integer>> postRating(List<MultipartFile> pics, ReviewDummyReq p){
        for(Long i=1L; i<901; i+=p.getNum()){
            p.setStrfId(i);
            reviewService.postRating(pics, p);
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), 1));
    }


    //strf 사진, 메뉴 등록
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> insPicAndMenuToStrf(StrfIdGetReq p){
        List<Long> strfIds= dataMapper.selectStrfId(p);
        if(strfIds==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}

        List<Map<String, Object>> picAndStrfIds = new ArrayList<>(strfIds.size());
        List<MenuDto> menus=p.getMenus()==null||p.getMenus().size()==0?new ArrayList<>():p.getMenus();
        List<Map<String,Object>> menuData =new ArrayList<>(strfIds.size()*menus.size());
        String sourcePath=String.format("%s/pics/%s/%s",myFileUtils.getUploadPath(),p.getCategory(),p.getPicFolder());
        String menuPath=String.format("%s/pics/%s/%s/menu",myFileUtils.getUploadPath(),p.getCategory(),p.getPicFolder());
        int strfCnt;
        int menuCnt;
        try {
             strfCnt=(int) myFileUtils.countFiles(sourcePath)-1;
             menuCnt=(int) myFileUtils.countFiles(menuPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(menuCnt!=menus.size()){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        for (long strfId : strfIds) {
            String middlePath = String.format("strf/%d", strfId);
            myFileUtils.makeFolders(middlePath);
            // ${file.directory}/pics/${category}/${picFolder} 내부의 파일을
            // ${file.directory}/strf/${strfId}/으로 파일을 붙여넣기
            String filePath = String.format("%s/strf/%d", myFileUtils.getUploadPath(), strfId);
            try {
                Path source = Paths.get(sourcePath);
                Path destination = Paths.get(filePath);
                myFileUtils.copyFolder(source, destination);
            } catch (IOException e) {
                //폴더 삭제 처리
                log.error("파일 저장 실패: {}", filePath, e);
                String delFolderPath = String.format("%s/%s", myFileUtils.getUploadPath(), middlePath);
                myFileUtils.deleteFolder(delFolderPath, true);
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
            }
            for (int i = 0; i < strfCnt; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("strfId", strfId);
                map.put("picName", String.format("%d.png", (i + 1)));
                picAndStrfIds.add(map);
            }
            if (menuCnt != 0) {
                for (int i = 0; i < menus.size(); i++) {
                    Map<String, Object> menuMap = new HashMap<>();
                    MenuDto menu = menus.get(i);
                    menuMap.put("strfId", strfId);
                    menuMap.put("title", menu.getTitle());
                    menuMap.put("price", menu.getPrice());
                    menuMap.put("menuPic", String.format("%d.png", (i + 1)));
                    menuData.add(menuMap);
                }
            }
        }
        int result= dataMapper.insStrfPic(picAndStrfIds);
        if (menuCnt != 0) { int menuResult= dataMapper.insMenu(menuData);
            if(menuResult==0){
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
            }
        }
        if(result==0){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),result));
    }

    //strf사진 삭제
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> delPicToStrf (StrfIdGetReq p){
        List<Long> strfIds= dataMapper.selectStrfId(p);
        if(strfIds==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}

        int result= dataMapper.delStrfIdPic(strfIds);
        if(result==0){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        for(int i=0; i<strfIds.size(); i++){
            String deletePath = String.format("%s/strf/%d", myFileUtils.getUploadPath(), strfIds.get(i));
            myFileUtils.deleteFolder(deletePath, true);
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),result));
    }

    //메뉴 데이터 넣기
    //@Transactional
//    public ResponseEntity<ResponseWrapper<Integer>> insMenu (List<MultipartFile> pics, MenuReq p){
//
//        List<Long> strfIds=dataMapper.selectStrfId(p);
//        if(strfIds==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}
//
//        // ${file.directory}/strf/${strfId}/menu/파일명
//        List<Map<String,Object>> menuData =new ArrayList<>(strfIds.size()*p.getMenus().size());
//        List<MenuDto> menus=p.getMenus();
//        for (long strfId:strfIds){
//            String middlePath=String.format("strf/%d/menu",strfId);
//            myFileUtils.makeFolders(middlePath);
//            for(int i=0; i<menus.size(); i++){
//                MenuDto menu=menus.get(i);
//                String savePicName=myFileUtils.makeRandomFileName(pics.get(i));
//                Map<String, Object> map = new HashMap<>();
//                map.put("strfId",strfId);
//                map.put("title",menu.getTitle());
//                map.put("price",menu.getPrice());
//                map.put("menuPic",savePicName);
//                menuData.add(map);
//                String filePath=String.format("%s/%s",middlePath,savePicName);
//                try{
//                    myFileUtils.transferTo(pics.get(i), filePath);
//                }catch(IOException e){
//                    //폴더 삭제 처리
//                    log.error("파일 저장 실패: {}", filePath, e);
//                    String delFolderPath=String.format("%s/%s", myFileUtils.getUploadPath(),middlePath);
//                    myFileUtils.deleteFolder(delFolderPath,true);
//                    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
//                            .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
//                }
//            }
//        }
//        log.info("menuData 3rows:{}",menuData.subList(0,8));
//        int result=dataMapper.insMenu(menuData);
//        if(result==0){
//            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
//                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
//        }
//        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),result));
//    }


    //menu사진 삭제
//    @Transactional
//    public ResponseEntity<ResponseWrapper<Integer>> delMenu (StrfIdGetReq p){
//        List<Long> strfIds= dataMapper.selectStrfId(p);
//        if(strfIds==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}
//
//        int result= dataMapper.delMenu(strfIds);
//        if(result==0){
//            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
//                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
//        }
//        for(int i=0; i<strfIds.size(); i++){
//            String deletePath = String.format("%s/strf/%d/menu", myFileUtils.getUploadPath(), strfIds.get(i));
//            myFileUtils.deleteFolder(deletePath, true);
//        }
//        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),result));
//    }

    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> updateInvalidProfilePics() {
        List<UserProfile> users = dataMapper.getAllUsersProfilePics(); // 모든 사용자 profilePic 조회
        if (users.isEmpty()) {
            return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), 0));
        }

        List<Long> updateUserIds = new ArrayList<>();

        // 기본 프로필 사진의 절대 경로 (파일 디렉토리 내)
        String defaultPicSourcePath = Paths.get(myFileUtils.getUploadPath(), "common", "user.png").toString();

        for (UserProfile user : users) {
            String profilePic = user.getProfilePic();
            if (profilePic == null || !profilePic.contains(".")) { // NULL 이거나 확장자 없음
                updateUserIds.add(user.getUserId());

                // 사용자별 폴더 생성 경로 (절대 경로로 설정)
                String userFolderPath = Paths.get(myFileUtils.getUploadPath(), "user", user.getUserId().toString()).toString(); // profile 폴더 없앰
                String defaultPicPath = Paths.get(userFolderPath, "user.png").toString();

                // 프로필 사진 폴더가 없으면 생성
                myFileUtils.makeFolders(userFolderPath);

                // 기본 이미지가 이미 존재하는지 확인하고 복사
                File sourceFile = new File(defaultPicSourcePath);
                File destinationFile = new File(defaultPicPath);

                // 디버깅: 경로 확인
                System.out.println("소스 파일 경로: " + sourceFile.getAbsolutePath());
                System.out.println("목적지 파일 경로: " + destinationFile.getAbsolutePath());

                if (!destinationFile.exists()) {
                    try {
                        // 목적지 폴더가 존재하는지 확인하고 없으면 생성
                        File parentFolder = destinationFile.getParentFile();
                        if (!parentFolder.exists()) {
                            parentFolder.mkdirs(); // 폴더 생성
                            System.out.println("목적지 폴더를 생성했습니다: " + parentFolder.getAbsolutePath());
                        }

                        // 기본 프로필 사진을 해당 사용자 폴더로 복사
                        Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);  // 기존 파일 덮어쓰기
                        System.out.println("기본 프로필 사진 복사 완료!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
                    }
                } else {
                    System.out.println("목적지 파일이 이미 존재합니다: " + destinationFile.getAbsolutePath());
                }
            }
        }

        if (!updateUserIds.isEmpty()) {
            int updatedCount = dataMapper.updateProfilePicsToDefault(updateUserIds, "user.png");
            return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), updatedCount));
        }

        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), 0));
    }
}
