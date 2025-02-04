package com.green.project_quadruaple.datamanager;


import com.green.project_quadruaple.common.MyFileUtils;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.datamanager.model.MenuDto;
import com.green.project_quadruaple.datamanager.model.StrfIdGetReq;
import com.green.project_quadruaple.review.ReviewService;
import com.green.project_quadruaple.review.model.ReviewPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class DataService {
    private final DataMapper dataMapper;
    private final MyFileUtils myFileUtils;
    private final ReviewService reviewService;

    public ResponseEntity<ResponseWrapper<Integer>> postRating(List<MultipartFile> pics, ReviewPostReq p){
        for(Long i=1L; i<901; i*=3){
            p.setStrfId(i);
            reviewService.postRating(pics, p);
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), 1));
    }


    //strf 사진 등록
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> insPicAndMenuToStrf(StrfIdGetReq p){
        List<Long> strfIds= dataMapper.selectStrfId(p);
        if(strfIds==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}

        List<Map<String, Object>> picAndStrfIds = new ArrayList<>(strfIds.size());
        List<Map<String,Object>> menuData =new ArrayList<>(strfIds.size()*p.getMenus().size());
        List<MenuDto> menus=p.getMenus();

        for (long strfId : strfIds) {
            String middlePath=String.format("strf/%d",strfId);
            myFileUtils.makeFolders(middlePath);
            // ${file.directory}/pics/${category}/${picFolder} 내부의 파일을
            // ${file.directory}/strf/${strfId}/으로 파일을 붙여넣기
            String sourcePath=String.format("%s/pics/%s/%s",myFileUtils.getUploadPath(),p.getCategory(),p.getPicFolder());
            String filePath=String.format("%s/strf/%d",myFileUtils.getUploadPath(),strfId);
            try{
                Path source = Paths.get(sourcePath);
                Path destination = Paths.get(filePath);
                myFileUtils.copyFolder(source, destination);
            }catch(IOException e){
                //폴더 삭제 처리
                log.error("파일 저장 실패: {}", filePath, e);
                String delFolderPath=String.format("%s/%s", myFileUtils.getUploadPath(),middlePath);
                myFileUtils.deleteFolder(delFolderPath,true);
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                            .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
            }
            int cnt;
            try {
                cnt=(int) myFileUtils.countFiles(sourcePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for(int i=0; i<cnt; i++){
                Map<String, Object> map = new HashMap<>();
                map.put("strfId",strfId);
                map.put("picName",String.format("%d.png",i+1));
                picAndStrfIds.add(map);
            }
            for(int i=0; i<menus.size(); i++){
                Map<String, Object> menuMap = new HashMap<>();
                MenuDto menu=menus.get(i);
                menuMap.put("strfId",strfId);
                menuMap.put("title",menu.getTitle());
                menuMap.put("price",menu.getPrice());
                menuMap.put("menuPic",String.format("%d.png",i+1));
                menuData.add(menuMap);
            }
        }
        log.info("picAndStrfIds:{}",picAndStrfIds.subList(0,3));
        int result= dataMapper.insStrfPic(picAndStrfIds);
        int menuResult= dataMapper.insMenu(menuData);
        if(result==0||menuResult==0){
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
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> delMenu (StrfIdGetReq p){
        List<Long> strfIds= dataMapper.selectStrfId(p);
        if(strfIds==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}

        int result= dataMapper.delMenu(strfIds);
        if(result==0){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        for(int i=0; i<strfIds.size(); i++){
            String deletePath = String.format("%s/strf/%d/menu", myFileUtils.getUploadPath(), strfIds.get(i));
            myFileUtils.deleteFolder(deletePath, true);
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),result));
    }
}
