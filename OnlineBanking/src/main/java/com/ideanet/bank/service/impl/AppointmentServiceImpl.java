package com.ideanet.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideanet.bank.domain.Appointment;
import com.ideanet.bank.repository.AppointmentRepository;
import com.ideanet.bank.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	public Appointment createAppointment(Appointment appointment) {
		
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> findAll() {
		
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment findAppointment(Long appointmentId) {
		Appointment appointment=appointmentRepository.findById(appointmentId).get();
		return appointment;
	}

	@Override
	public void comfirmAppointment(Long appointmentId) {
		Appointment appointment=findAppointment(appointmentId);
		appointment.setComfirmed(true);
		appointmentRepository.save(appointment);
		
	}

}
