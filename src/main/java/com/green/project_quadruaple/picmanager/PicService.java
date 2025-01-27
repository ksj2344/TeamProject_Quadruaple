package com.green.project_quadruaple.picmanager;


import com.green.project_quadruaple.common.MyFileUtils;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.home.res.MyPageRes;
import com.green.project_quadruaple.picmanager.model.StrfPicReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class PicService {
    private final PicMapper picMapper;
    private final MyFileUtils myFileUtils;


    //strf 사진 등록
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> insPicToStrf (List<MultipartFile> pics, StrfPicReq p){
        List<Long> strfIds=picMapper.selectStrfId(p);
        if(strfIds==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}

        // ${file.directory}/strf/${strfId}/파일명
        //
        List<String> picAndStrfId=new ArrayList<>(pics.size()*strfIds.size());

        for (int i = 0; i < strfIds.size(); i++) {
            String middlePath=String.format("strf/%d",strfIds.get(i));
            myFileUtils.makeFolders(middlePath);
            for(MultipartFile pic: pics){
                String savePicName=myFileUtils.makeRandomFileName(pic);
                picAndStrfId.add(String.format("%s , %d",savePicName,strfIds.get(i)));
                String filePath=String.format("%s/%s",middlePath,savePicName);
                try{
                    myFileUtils.transferTo(pic, filePath);
                }catch(IOException e){
                    //폴더 삭제 처리
                    String delFolderPath=String.format("%s/%s", myFileUtils.getUploadPath(),middlePath);
                    myFileUtils.deleteFolder(delFolderPath,true);
                    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                            .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
                }
            }
        }
        int result=picMapper.insStrfPic(picAndStrfId);
        if(result==0){
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),result));
    }

    //strf사진 삭제
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> delPicToStrf (StrfPicReq p){
        List<Long> strfIds=picMapper.selectStrfId(p);
        if(strfIds==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}

        int result=picMapper.delStrfIdPic(strfIds);
        if(result==0){
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        for(int i=0; i<strfIds.size(); i++){
            String deletePath = String.format("%s/strf/%d", myFileUtils.getUploadPath(), strfIds.get(i));
            myFileUtils.deleteFolder(deletePath, true);
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),result));
    }
}
