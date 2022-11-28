package com.example.itnews.mainUtils;

import com.example.itnews.Models.Headlines;

import java.util.List;

public interface OnFetchDataListener<ApiResponse> {
    void onFetchData(List<Headlines> list, String message);
    void onError(String message);
}
