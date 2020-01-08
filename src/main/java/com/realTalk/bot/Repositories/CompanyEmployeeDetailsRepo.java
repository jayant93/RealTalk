package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.CompanyEmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyEmployeeDetailsRepo extends JpaRepository<CompanyEmployeeDetails,Long> {

    @Query("Select emp from CompanyEmployeeDetails emp where SlackId = :userId AND channelId = :channelId")
    CompanyEmployeeDetails findBySlackIdAndChannelId(@Param("userId") String userId,@Param("channelId") String channelId);

}
