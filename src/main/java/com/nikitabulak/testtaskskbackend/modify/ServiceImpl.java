package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.exception.MissingIdException;
import com.nikitabulak.testtaskskbackend.exception.TeapotException;
import com.nikitabulak.testtaskskbackend.modify.dto.RequestDto;
import com.nikitabulak.testtaskskbackend.modify.dto.ResponseDto;
import com.nikitabulak.testtaskskbackend.modify.model.DefaultModel;
import com.nikitabulak.testtaskskbackend.modify.model.Obj;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private final Repository repository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public ResponseDto increaseCurrentWithQuery(RequestDto requestDto) {
        try {
            jdbcTemplate.update("Update sk_example_table" +
                    " SET obj = jsonb_set(obj, '{current}', ((select ((json_agg(e.obj) -> 0) ->> 'current')::numeric" +
                    " FROM sk_example_table e) + ?)::text::jsonb, false)" +
                    " WHERE id = ?", requestDto.getAdd(), requestDto.getId());
            return ModelMapper.toResponseDto(repository.findById(requestDto.getId()).orElseThrow(TeapotException::new));
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new TeapotException(e);
        }
    }

    @Override
    public ResponseDto getCurrent(int id) {
        return ModelMapper.toResponseDto(repository.findById(id).orElseThrow(() -> new TeapotException(new MissingIdException())));
    }

    @Override
    public void resetCurrent(int id) {
        repository.save(new DefaultModel(1, new Obj(0)));
    }

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
