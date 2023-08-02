package com.smartscheduler.smart.repository;

import com.smartscheduler.smart.model.Appointment;
import com.smartscheduler.smart.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    @Query(value = "SELECT * FROM schedule as a WHERE a.agent_id = :agentId AND EXTRACT(DAY FROM a.date) = :day AND EXTRACT(MONTH FROM a.date) = :month AND EXTRACT(YEAR FROM a.date) = :year", nativeQuery = true)
    Schedule getScheduleAgentOnDate(@Param("agentId") Integer agentId, @Param("day")  Integer day, @Param("month")  Integer month, @Param("year")  Integer year);

}
