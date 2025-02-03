package com.green.project_quadruaple.search.model;

import io.jsonwebtoken.lang.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StrfResponse {
    private long strfId;
    private String strfTitle;
    private String category;
    private int price;
    private String strfPic;

}
