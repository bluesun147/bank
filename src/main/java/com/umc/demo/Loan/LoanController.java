package com.umc.demo.Loan;

import com.umc.demo.CreditCard.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
//@RestController
@Controller
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanRepository loanRepository;


    //대출 등록 폼(완료)
    @GetMapping("/signinForm")
    public String signinForm(){
        return "loan/signinForm";
    }

    // 대출 등록(완료)
    @PostMapping("/signin")
    public String createLoan(
            @RequestParam("socialNumber") String socialNumber,
            @RequestParam("branchNumber") int branchNumber,
            @RequestParam("amount") double amount,
            @RequestParam("loandate") String loanDate) {
        Loan loan = new Loan();
        loan.setSocialnumber(socialNumber);
        loan.setBranchnumber(branchNumber);
        loan.setAmount(amount);
        loan.setLoandate(LocalDate.parse(loanDate));
        loanRepository.save(loan);
        return "redirect:/loan/userForm";
    }

    //고객(주민번호로)의 대출 정보 조회 폼(완료)
    @GetMapping("/userForm")
    public String nameForm(){
        return "loan/userForm";
    }

    // 고객(주민번호로)의 대출 정보 조회(완료) ---> 내역 조회 말고 그냥 정보 한번에 조회. 대출 상환(이자x) 하면 그냥 amount 줄어드는 식
    @PostMapping("/user")
    public String getUsersLoan(@RequestParam("socialNumber") String socialNumber, Model model) {
        model.addAttribute("users", loanRepository.getUsersLoan(socialNumber));
        return "loan/user";
    }

    //특정 대출의 남은 amount 조회 폼(완료)
    @GetMapping("/amountForm")
    public String amountForm(){
        return "loan/amountForm";
    }

    // 특정 대출의 남은 amount 조회(완료)
    @PostMapping("/amount")
    public String getLoanAmount(@RequestParam("loanNumber") int loanNumber, Model model) {
        model.addAttribute("amount", new BigDecimal(loanRepository.getLoanAmount(loanNumber)));
        return "loan/amount";
    }

    //특정 대출의 대출 상환 폼(완료)
    @GetMapping("/repaymentForm")
    public String repaymentForm(){
        return "loan/repaymentForm";
    }

    //특정 대출의 대출 상환(완료)
    @PostMapping("/repayment")
    public String repayment(
            @RequestParam("loanNumber") int loanNumber,
            @RequestParam("money") double money, Model model) {
        double repaymentamount = loanRepository.getLoanAmount(loanNumber);
        double result = repaymentamount - money;

        loanRepository.repayment(result, loanNumber);
        model.addAttribute("amount", new BigDecimal(loanRepository.getLoanAmount(loanNumber)));
        return "loan/amount";
    }
}