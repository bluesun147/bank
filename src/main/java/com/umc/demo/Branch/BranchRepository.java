package com.umc.demo.Branch;


import com.umc.demo.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer>{
    // 전체 브랜치 조회
    @Query(value = "select * from Branch", nativeQuery = true)
    public List<Branch> getAllBranchs();


}