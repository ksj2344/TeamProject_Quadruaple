package com.green.project_quadruaple.search.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.PortResolverImpl;

@Getter
@Setter
public class SearchInsReq {
    private String txt;
    private Long userId;

}
