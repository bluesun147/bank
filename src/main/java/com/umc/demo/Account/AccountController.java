package com.umc.demo.Account;

import com.umc.demo.Transaction.Transaction;
import com.umc.demo.Transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
//@RestController
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    // 계좌 개설
    @PostMapping("/signin")
    public void createAccount(
            @RequestParam("socialNumber") String socialNumber,
            @RequestParam("branchnumber") int branchnumber,
            @RequestParam("type") String type,
            @RequestParam("balance") double balance,
            @RequestParam("cardappstatus") boolean cardappstatus) {
        Account ac = new Account();
        ac.setSocialnumber(socialNumber);
        ac.setBranchnumber(branchnumber);
        ac.setType(type);
        ac.setBalance(balance);
        ac.setCardappstatus(cardappstatus);
        ac.setOpendate(LocalDate.now());
        accountRepository.save(ac);
    }


    // 모든 계좌 조회(완료)
    @GetMapping("/all")
    public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountRepository.getAllAccounts());
        return "account/all";
    }


    //특정 사용자 계좌 조회 폼(완료)
    @GetMapping("/userForm")
    public String userForm(){
        return "account/userForm";
    }


    // 특정 사용자 계좌 조회(완료)
    @PostMapping("/user")
    public String getAccounts(@RequestParam("socialNumber") String socialNumber, Model model) {
        model.addAttribute("accounts",accountRepository.getAccount(socialNumber));
        return "account/user";
    }

    //특정 계좌 잔고 조회 폼(완료)
    @GetMapping("/balanceForm")
    public String balanceForm(){
        return "account/balanceForm";
    }

    // 특정 계좌 잔고 조회(완료)
    @PostMapping("/balance")
    public String getAccountBalance(@RequestParam("accountNumber") int accountNumber, Model model) {
        model.addAttribute("balance",accountRepository.getAccountBalance(accountNumber));
        return "account/balance";
    }

    // 입출금시 거래내역 테이블에도 데이터 들어가야 함.



    // 입금
    @GetMapping("/deposit")
    public double deposit(
            @RequestParam("deposit") double deposit,
            @RequestParam("accountNumber") int accountNumber) {

        double accountBalance = accountRepository.getAccountBalance(accountNumber); // 원금
        double result = accountBalance + deposit; // 원금 + 입금액
        accountRepository.deposit(result, accountNumber); // 입금

        // 거래내역 테이블에 데이터 입력
//        transactionRepository.putTrans(accountNumber, deposit, "2022-11-21 22:20:47");
        // transactionRepository.putTrans(accountNumber, deposit);
        Transaction tr = new Transaction();
        tr.setAccountnumber(accountNumber);
        tr.setTransactionamount(deposit);
        transactionRepository.save(tr);

        return accountRepository.getAccountBalance(accountNumber); // 입금 후 잔액 반환
    }

    // 출금
    @GetMapping("/withdraw")
    public double withdraw(
            @RequestParam("withdraw") double withdraw,
            @RequestParam("accountNumber") int accountNumber) {

        double accountBalance = accountRepository.getAccountBalance(accountNumber); // 원금
        double result = accountBalance - withdraw; // 원금 - 출금액
        accountRepository.withdraw(result, accountNumber); // 출금

        // 거래내역 테이블에 데이터 입력
        //transactionRepository.putTrans(accountNumber, (-1*withdraw));
        Transaction tr = new Transaction();
        tr.setAccountnumber(accountNumber);
        tr.setTransactionamount((-1)*withdraw);
        transactionRepository.save(tr);

        return accountRepository.getAccountBalance(accountNumber); // 출금 후 잔액 반환
    }


    //계좌 이체 폼(완료)
    @GetMapping("/transferForm")
    public String transferForm(){
        return "account/transferForm";
    }

    // 계좌 이체(완료)
    @PostMapping("/transfer")
    public String transfer(
            @RequestParam("money") double money,
            @RequestParam("accountNumber1") int accountNumber1,
            @RequestParam("accountNumber2") int accountNumber2) {
         this.withdraw(money, accountNumber1); // 계좌1에서 출금
         this.deposit(money, accountNumber2); // 계좌2에 입금
        return "account/balanceForm";
    }

    //계좌에 연결된 카드 조회 폼(완료)
    @GetMapping("/cardForm")
    public String cardForm(){
        return "account/cardForm";
    }

    // 계좌에 연결된 카드 조회 (오류)
    @PostMapping("/card")
    public String getCards(@RequestParam("accountNumber") int accountNumber, Model model) {
        model.addAttribute("cards", accountRepository.getCards(accountNumber));
        return "account/card";
    }

    //특정 브랜치에서(branchNumber 사용) 개설된 계좌 조회 폼(완료)
    @GetMapping("/branchForm")
    public String branchAccountsForm(){
        return "account/branchForm";
    }

    //특정 브랜치에서(branchNumber 사용) 개설된 계좌 조회(완료)
    @PostMapping("/branch")
    public String getBranchAccounts(@RequestParam("branchNumber") int branchNumber, Model model) {
        model.addAttribute("branchs", accountRepository.getBranchAccounts(branchNumber));
        return "account/branch";
    }
}