package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.exception.MissingIdException;
import com.nikitabulak.testtaskskbackend.exception.TeapotException;
import com.nikitabulak.testtaskskbackend.modify.dto.RequestDto;
import com.nikitabulak.testtaskskbackend.modify.dto.ResponseDto;
import com.nikitabulak.testtaskskbackend.modify.model.DefaultModel;
import com.nikitabulak.testtaskskbackend.modify.model.Obj;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@org.springframework.stereotype.Service
@Slf4j
public class ServiceImpl implements Service {
    private final Repository repository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public ResponseDto increaseCurrent(RequestDto requestDto) {
        Optional<DefaultModel> modelOptional = repository.findDefaultModelById(requestDto.getId());
        if (modelOptional.isPresent()) {
            DefaultModel model = modelOptional.get();
            model.add(requestDto.getAdd());
            model = repository.save(model);
            return ModelMapper.toResponseDto(model);
        } else {
            log.warn("There is no record with id = {}", requestDto.getId());
            throw new TeapotException();
        }
    }

    @Override
    @Transactional
    public ResponseDto increaseCurrentWithoutSelectForUpdate(RequestDto requestDto) {
        Optional<DefaultModel> modelOptional = repository.findById(requestDto.getId());
        if (modelOptional.isPresent()) {
            DefaultModel model = modelOptional.get();
            model.add(requestDto.getAdd());
            model = repository.save(model);
            return ModelMapper.toResponseDto(model);
        } else {
            log.warn("There is no record with id = {}", requestDto.getId());
            throw new TeapotException();
        }
    }

    @Override
    @Transactional
    public ResponseDto increaseCurrentWithOneQuery(RequestDto requestDto) {
        try {
            jdbcTemplate.update("UPDATE sk_example_table " +
                    "SET obj = ('{\"current\": ' || ((obj->>'current')::numeric + ?) || '}')::jsonb " +
                    "WHERE id = ?", requestDto.getAdd(), requestDto.getId());
            return ModelMapper.toResponseDto(repository.findById(requestDto.getId()).orElseThrow(TeapotException::new));
        } catch (DataAccessException e) {
            log.warn("There is DataAccessException occurred while adding {} to record with id = {}", requestDto.getAdd(), requestDto.getId(), e);
            throw new TeapotException(e);
        }
    }

    @Override
    @Transactional
    public ResponseDto increaseCurrentWithQueriesWithoutSelectForUpdate(RequestDto requestDto) {
        try {
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM sk_example_table WHERE id = ?", requestDto.getId());
            sqlRowSet.next();
            double current = Double.parseDouble(sqlRowSet.getString(2).split(": ")[1].replace("}", ""));
            jdbcTemplate.update("UPDATE sk_example_table " +
                    "SET obj = ('{\"current\": ' || (? + ?) || '}')::jsonb " +
                    "WHERE id = ?", current, requestDto.getAdd(), requestDto.getId());
            return ModelMapper.toResponseDto(repository.findById(requestDto.getId()).orElseThrow(TeapotException::new));
        } catch (DataAccessException e) {
            log.warn("There is DataAccessException occurred while adding {} to record with id = {}", requestDto.getAdd(), requestDto.getId(), e);
            throw new TeapotException(e);
        }
    }

    @Override
    @Transactional
    public ResponseDto increaseCurrentWithQueriesWithSelectForUpdate(RequestDto requestDto) {
        try {
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM sk_example_table WHERE id = ? FOR UPDATE", requestDto.getId());
            sqlRowSet.next();
            double current = Double.parseDouble(sqlRowSet.getString(2).split(": ")[1].replace("}", ""));
            jdbcTemplate.update("UPDATE sk_example_table " +
                    "SET obj = ('{\"current\": ' || (? + ?) || '}')::jsonb " +
                    "WHERE id = ?", current, requestDto.getAdd(), requestDto.getId());
            return ModelMapper.toResponseDto(repository.findById(requestDto.getId()).orElseThrow(TeapotException::new));
        } catch (DataAccessException e) {
            log.warn("There is DataAccessException occurred while adding {} to record with id = {}", requestDto.getAdd(), requestDto.getId(), e);
            throw new TeapotException(e);
        }
    }

    @Override
    @Transactional
    public ResponseDto getCurrent(int id) {
        return ModelMapper.toResponseDto(repository.findById(id).orElseThrow(() -> new TeapotException(new MissingIdException())));
    }

    @Override
    @Transactional
    public void resetCurrent(int id) {
        repository.save(new DefaultModel(1, new Obj(0)));
    }
}
