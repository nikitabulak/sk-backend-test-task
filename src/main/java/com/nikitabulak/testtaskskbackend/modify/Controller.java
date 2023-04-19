package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.modify.dto.RequestDto;
import com.nikitabulak.testtaskskbackend.modify.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/modify")
public class Controller {
    private final Service modifyService;

    @PostMapping
    public ResponseDto increaseCurrent(@Valid @RequestBody RequestDto requestDto) {
        return modifyService.increaseCurrent(requestDto);
    }

    @PostMapping("/withoutSFU")
    public ResponseDto increaseCurrentWithoutSelectForUpdate(@Valid @RequestBody RequestDto requestDto) {
        return modifyService.increaseCurrentWithoutSelectForUpdate(requestDto);
    }

    @PostMapping("/native")
    public ResponseDto increaseCurrentWithOneQuery(@Valid @RequestBody RequestDto requestDto) {
        return modifyService.increaseCurrentWithOneQuery(requestDto);
    }

    @PostMapping("/nativeWithSFU")
    public ResponseDto increaseCurrentWithQueriesWithSelectForUpdate(@Valid @RequestBody RequestDto requestDto) {
        return modifyService.increaseCurrentWithQueriesWithSelectForUpdate(requestDto);
    }

    @PostMapping("/nativeWithoutSFU")
    public ResponseDto increaseCurrentWithQueriesWithoutSelectForUpdate(@Valid @RequestBody RequestDto requestDto) {
        return modifyService.increaseCurrentWithQueriesWithoutSelectForUpdate(requestDto);
    }

    @GetMapping
    public ResponseDto getCurrent(@RequestParam int id) {
        return modifyService.getCurrent(id);
    }

    @PutMapping
    public void resetCurrent(@RequestParam int id) {
        modifyService.resetCurrent(id);
    }
}
