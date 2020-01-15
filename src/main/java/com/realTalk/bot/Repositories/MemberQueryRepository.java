package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.MemberQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberQueryRepository extends JpaRepository<MemberQuery,String> {

    @Query(value = "Select * from member_query where member_user = :userId",nativeQuery = true)
    MemberQuery findByMemberId(@Param("userId") String userId);

    @Query(value="Select * from member_query where on_boarding_status = 'COMPLETE' ",nativeQuery = true)
    List<MemberQuery> findOnBoardedMembers();

}
