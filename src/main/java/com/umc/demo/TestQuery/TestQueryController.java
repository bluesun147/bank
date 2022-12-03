package com.umc.demo.TestQuery;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/test")
//@RestController
@Controller
public class TestQueryController {
    @Autowired
    TestQueryRepository testQueryRepository;


    // 테스트 쿼리1
    //1. 이름이 선민우인 고객의 모든 계좌와 현재 잔액, 계좌 개설일을 표시하라.
    @GetMapping("/1")
    public String test1() {
        testQueryRepository.test1();
        return "test/1";
    }

    // 테스트 쿼리2
    //2. 1의 결과를 계좌 개설이 빠른 순으로 정렬하라.
    @GetMapping("/2")
    public String test2() {
        testQueryRepository.test2();
        return "test/2";
    }

    // 테스트 쿼리3
    //3. 이름이 선민우인 고객의 1번 계좌의 과거 한달간 입출금 내역을 출력하라.
    @GetMapping("/3")
    public String test3() {
        testQueryRepository.test3();
        return "test/3";
    }

    // 테스트 쿼리4
    //4. 선민우 고객이 보유한 모든 신용 카드와 연결 계좌, 현재까지의 사용 금액 및사용 한도를 출력하라.
    @GetMapping("/4")
    public String test4() {
        testQueryRepository.test4();
        return "test/4";
    }
}

