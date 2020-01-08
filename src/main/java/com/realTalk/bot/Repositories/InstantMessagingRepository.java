package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.InstantMessagingData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstantMessagingRepository extends JpaRepository<InstantMessagingData,String> {

    InstantMessagingData findByUser(String id);
}
