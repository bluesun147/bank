package com.umc.demo.TestQuery;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class TestQueryController {
    @Autowired
    TestQueryRepository testQueryRepository;

    // 테스트 쿼리1
    //1. 이름이 선민우인 고객의 모든 계좌와 현재 잔액, 계좌 개설일을 표시하라.
    @GetMapping("/1")
    public List<Object> test1() {
        return testQueryRepository.test1();
    }

    // 테스트 쿼리2
    //2. 1의 결과를 계좌 개설이 빠른 순으로 정렬하라.
    @GetMapping("/2")
    public List<Object> test2() {
        return testQueryRepository.test2();
    }

    // 테스트 쿼리3
    //3. 이름이 선민우인 고객의 1번 계좌의 과거 한달간 입출금 내역을 출력하라
    @GetMapping("/3")
    public List<Object> test3() {
        return testQueryRepository.test3();
    }

    // 테스트 쿼리4
    //4. 선민우 고객이 보유한 모든 신용 카드와 연결 계좌, 현재까지의 사용 금액 및사용 한도를 출력하라.
    @GetMapping("/4")
    public List<Object> test4() {
        return testQueryRepository.test4();
    }
}

