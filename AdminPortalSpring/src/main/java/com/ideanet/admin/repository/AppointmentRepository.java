package com.ideanet.admin.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.admin.domain.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment,Long>{

	 List<Appointment> findAll();
}
