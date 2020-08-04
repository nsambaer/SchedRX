package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Office;

public interface OfficeDAO {

	public List<Office> getAllOffices();
	
	public Office getOfficeById(Long officeId);
	
	public Office getOfficeByDoctor(Long doctorId);
	
	
	
	
}
