package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.modify.dto.ResponseDto;
import com.nikitabulak.testtaskskbackend.modify.model.DefaultModel;

public class ModelMapper {
    public static ResponseDto toResponseDto(DefaultModel model) {
        return new ResponseDto(model.getObj().getCurrent());
    }
}
