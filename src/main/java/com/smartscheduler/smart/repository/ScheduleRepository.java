package com.smartscheduler.smart.repository;

import com.smartscheduler.smart.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
}
