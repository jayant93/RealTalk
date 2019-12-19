package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.CompanyEmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyEmployeeDetailsRepo extends JpaRepository<CompanyEmployeeDetails,Long> {
}
