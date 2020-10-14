package com.ideanet.admin.service;

import java.util.List;

import com.ideanet.admin.domain.Appointment;

public interface AppointmentService {
	
	List<Appointment> findAll();
	
	Appointment findAppointment(Long appointmentId);
	
	void comfirmAppointment(Long appointmentId);
	
	
}
