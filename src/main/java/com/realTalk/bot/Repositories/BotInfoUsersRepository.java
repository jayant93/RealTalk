package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.BotInfoUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotInfoUsersRepository extends JpaRepository<BotInfoUsers,Long> {
}
