package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.DailyPulseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyPulseResponseRepository extends JpaRepository<DailyPulseResponse,Long> {

    @Query(value = "Select r from DailyPulseResponse r where r.userId = :user_id and r.ActiveStatus = TRUE")
    DailyPulseResponse findActiveResponse(@Param("user_id") String user_id);
}
