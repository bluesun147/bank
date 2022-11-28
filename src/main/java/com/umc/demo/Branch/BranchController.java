package com.umc.demo.Branch;

import com.umc.demo.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
//@RestController
@Controller
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    BranchRepository branchRepository;

    // 전체 브랜치 조회(완료)
    @GetMapping("/all")
    public String getAllAccounts(Model model) {
        model.addAttribute("branchs", branchRepository.getAllBranchs());
        return "branch/all";
    }
}