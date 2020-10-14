package com.ideanet.admin.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideanet.admin.domain.Appointment;
import com.ideanet.admin.repository.AppointmentRepository;
import com.ideanet.admin.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	
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
