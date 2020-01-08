package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.ChannelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChannelInfoRepo extends JpaRepository<ChannelInfo,Long> {


    @Query(value = "select count(*) from channel_info where channel_id = :channelId",nativeQuery = true)
    int getChannelCount(@Param("channelId") String channelId);

    ChannelInfo findByChannelId(String channelId);


}
