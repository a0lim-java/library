package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

@RestController // 해당 class를 api의 진입 지점으로 만듦(주어진 class를 Controller로 등록)
public class CalculatorController {
    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request){
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) { // Post: @RequestBody(json을 객체로 변환함)
        return request.getNumber1() * request.getNumber2();
    }
}
