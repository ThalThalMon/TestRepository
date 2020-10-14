package com.ideanet.bank.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.bank.domain.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment,Long>{

	 List<Appointment> findAll();
}
