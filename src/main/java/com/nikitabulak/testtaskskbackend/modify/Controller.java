package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.modify.dto.RequestDto;
import com.nikitabulak.testtaskskbackend.modify.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/modify")
public class Controller {
    private final Service modifyService;

    @PostMapping
    public ResponseDto increaseCurrent(@RequestBody RequestDto requestDto) {
        return modifyService.increaseCurrent(requestDto);
    }
}
