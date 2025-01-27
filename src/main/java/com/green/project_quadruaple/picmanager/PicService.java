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


    //strf에 사진 넣기
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> insPicToStrf (List<MultipartFile> pics, StrfPicReq p){
        List<Long> strfIds=picMapper.selectStrfId(p);
        if(strfIds==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}

        // ${file.directory}/strf/${strfId}/파일명

        List<String> picNameList=new ArrayList<>(pics.size());
        for(MultipartFile pic: pics){
            String savePicName=myFileUtils.makeRandomFileName(pic);
            picNameList.add(savePicName);
        }

        for (int i = 0; i < strfIds.size(); i++) {
            String middlePath=String.format("strf/%d",strfIds.get(i));
            myFileUtils.makeFolders(middlePath);
            for(int j=0; j<pics.size(); j++){
                String filePath=String.format("%s/%s",middlePath,picNameList.get(j));
                try{
                    myFileUtils.transferTo(pics.get(j), filePath);
                }catch(IOException e){
                    //폴더 삭제 처리
                    String delFolderPath=String.format("%s/%s", myFileUtils.getUploadPath(),middlePath);
                    myFileUtils.deleteFolder(delFolderPath,true);
                    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                            .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
                }
            }

        }
        for(String pic:picNameList){
            int result=picMapper.insStrfPic(pic,strfIds);
            if(result==0){
                ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
            }
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),1));
    }
}
