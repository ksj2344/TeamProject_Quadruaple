package com.green.project_quadruaple.search.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;

import com.green.project_quadruaple.common.model.ResultResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class SearchResponse extends ResultResponse {

    @JsonProperty("isMore")
    private final boolean isMore;

    @JsonProperty("wishList")
    private final List<Object> wishList;

    @JsonProperty("hotKeyWords")
    private final List<String> hotKeyWords;

    @JsonProperty("searchedTxts")
    private final List<String> searchedTxts;

    // 명시적으로 모든 필드를 포함한 생성자 작성
    public SearchResponse(String code, boolean isMore, List<Object> wishList, List<String> hotKeyWords, List<String> searchedTxts) {
        super(code); // 부모 클래스 ResultRespons의 생성자 호출
        this.isMore = isMore;
        this.wishList = wishList;
        this.hotKeyWords = hotKeyWords;
        this.searchedTxts = searchedTxts;
    }

    public static SearchResponse success(
            boolean isMore,
            List<Object> wishList,
            List<String> hotKeyWords,
            List<String> searchedTxts) {
        return new SearchResponse(ResponseCode.OK.getCode(), isMore, wishList, hotKeyWords, searchedTxts);
    }

    public static SearchResponse serverError() {
        return new SearchResponse(ResponseCode.SERVER_ERROR.getCode(), false, null, null, null);
    }
}

