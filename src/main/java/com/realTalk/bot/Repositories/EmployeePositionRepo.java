package com.realTalk.bot.Repositories;

import com.realTalk.bot.Model.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePositionRepo extends JpaRepository<EmployeePosition,Long> {

}
