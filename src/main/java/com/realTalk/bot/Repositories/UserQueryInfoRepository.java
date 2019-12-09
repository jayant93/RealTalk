package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.UserQueryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UserQueryInfoRepository extends JpaRepository<UserQueryInfo,Long> {
}
