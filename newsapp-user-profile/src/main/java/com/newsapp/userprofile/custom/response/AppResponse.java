package com.newsapp.userprofile.custom.response;

import com.newsapp.userprofile.model.Action;
import com.newsapp.userprofile.model.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppResponse<T> {
    private Action actionPerformed;
    private Result result;
    private T actionResponse;
}
