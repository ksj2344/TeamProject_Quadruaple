package com.green.project_quadruaple.strf.model;


import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class StrfSelReq {



    @NotNull
    private long strfId;
}
