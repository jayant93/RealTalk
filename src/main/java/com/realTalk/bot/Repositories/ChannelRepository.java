package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,String> {

}
