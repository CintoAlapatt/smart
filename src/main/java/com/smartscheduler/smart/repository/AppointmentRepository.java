package com.smartscheduler.smart.repository;

import com.smartscheduler.smart.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("SELECT s FROM Appointment s WHERE s.id=1")
    Optional<Appointment> findAppointmentByEstimateHrs(int id);
    @Query("SELECT  a FROM Appointment a WHERE MONTH(a.dateTime) = :month AND YEAR(a.dateTime) = COALESCE(:year, YEAR(CURRENT_DATE))")
    List<Appointment> getAllAppointmentsByMonth(@Param("month") Integer month,@Param("year") Integer year);
    @Query(value = "SELECT * FROM appointment as a WHERE a.agent_id = :agentId AND EXTRACT(DAY FROM a.date_time) = :day AND EXTRACT(MONTH FROM a.date_time) = :month AND EXTRACT(YEAR FROM a.date_time) = :year", nativeQuery = true)
    List<Appointment> getAllAppointmentsByAgentAndDate(@Param("agentId") Integer agentId,@Param("day")  Integer day,@Param("month")  Integer month,@Param("year")  Integer year);
}


