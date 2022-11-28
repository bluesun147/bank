package com.umc.demo.Transaction;

import com.umc.demo.Loan.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
//@RestController
@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    //특정 계좌 거래 내역 조회 폼(완료)
    @GetMapping("/accountForm")
    public String transactionForm(){
        return "transaction/accountForm";
    }

    // 특정 계좌 거래 내역 조회(완료) (누구한테 보낸지는 말고 입금, 출금만 기록)
    @PostMapping("/account") // <>안의 타입과 리포지토리 extends 타입과 맞아야 함
    public String getAccountTrans(@RequestParam("accountNumber") int accountNumber, Model model) {
        model.addAttribute("transactions",transactionRepository.getAccountTrans(accountNumber));
        return "transaction/account";
    }

    // 거래 내역 작성
    @PostMapping("/")
    public void putTrans(
            @RequestParam("accountNumber") int accountNumber,
            @RequestParam("money") double money) {
        transactionRepository.putTrans(accountNumber, money);
    }
}