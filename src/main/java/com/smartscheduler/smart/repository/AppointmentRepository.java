package com.smartscheduler.smart.repository;

import com.smartscheduler.smart.model.Address;
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
    @Query("SELECT  a FROM Appointment a WHERE MONTH(a.appointmentDate) = :month AND YEAR(a.appointmentDate) = COALESCE(:year, YEAR(CURRENT_DATE))")
    List<Appointment> getAllAppointmentsByMonth(@Param("month") Integer month,@Param("year") Integer year);
    @Query(value = "SELECT * FROM appointment as a WHERE a.agent_id = :agentId AND EXTRACT(DAY FROM a.appointment_date) = :day AND EXTRACT(MONTH FROM a.appointment_date) = :month AND EXTRACT(YEAR FROM a.appointment_date) = :year", nativeQuery = true)
    List<Appointment> getAllAppointmentsByAgentAndDate(@Param("agentId") Integer agentId,@Param("day")  Integer day,@Param("month")  Integer month,@Param("year")  Integer year);

    @Query("SELECT a FROM Appointment a WHERE a.serviceLocation.id = :addressId")
    Optional<Appointment> findAppointmentByAddressId(@Param("addressId") Integer addressId);

    Optional<Appointment> findById(Long id);

}


