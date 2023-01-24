package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.exception.TeapotException;
import com.nikitabulak.testtaskskbackend.modify.dto.RequestDto;
import com.nikitabulak.testtaskskbackend.modify.dto.ResponseDto;
import com.nikitabulak.testtaskskbackend.modify.model.DefaultModel;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private final Repository repository;

    @Override
    @Transactional
    public ResponseDto increaseCurrent(RequestDto requestDto) {
        Optional<DefaultModel> modelOptional = repository.findById(requestDto.getId());
        if (modelOptional.isPresent()) {
            DefaultModel model = modelOptional.get();
            model.getObj().increaseCurrent(requestDto.getAdd());
            model = repository.save(model);
            return ModelMapper.toResponseDto(model);
        } else {
            throw new TeapotException();
        }
    }
}
