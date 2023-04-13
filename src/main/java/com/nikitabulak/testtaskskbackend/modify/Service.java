package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.modify.dto.RequestDto;
import com.nikitabulak.testtaskskbackend.modify.dto.ResponseDto;

public interface Service {
    ResponseDto increaseCurrent(RequestDto requestDto);

    ResponseDto increaseCurrentWithQuery(RequestDto requestDto);

    ResponseDto getCurrent(int id);

    void resetCurrent(int id);
}
