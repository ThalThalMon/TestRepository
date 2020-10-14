package com.ideanet.bank.service;

import java.util.List;

import com.ideanet.bank.domain.Appointment;

public interface AppointmentService {

	Appointment createAppointment(Appointment appointment);
	
	List<Appointment> findAll();
	
	Appointment findAppointment(Long appointmentId);
	
	void comfirmAppointment(Long appointmentId);
	
	
}
