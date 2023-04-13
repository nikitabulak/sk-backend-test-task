package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.modify.dto.RequestDto;
import com.nikitabulak.testtaskskbackend.modify.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/modify")
public class Controller {
    private final Service modifyService;

    @PostMapping
    public ResponseDto increaseCurrent(@RequestBody RequestDto requestDto) {
        return modifyService.increaseCurrent(requestDto);
    }

    @GetMapping
    public ResponseDto getCurrent(@RequestParam int id) {
        return modifyService.getCurrent(id);
    }

    @PutMapping
    public void resetCurrent(@RequestParam int id) {
        modifyService.resetCurrent(id);
    }

    @PostMapping("/native")
    public ResponseDto increaseCurrentWithQuery(@RequestBody RequestDto requestDto) {
        return modifyService.increaseCurrentWithQuery(requestDto);
    }
}
