package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.strf.model.StrfSelReq;
import com.green.project_quadruaple.strf.model.StrfSelRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class StrfService {
    private final StrfMapper mapper;

    List<StrfSelRes> getDetail(StrfSelReq req){
        if (req.getStrfId() <= 0){

        }




        return null;
    }
}
