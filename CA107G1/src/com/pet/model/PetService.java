package com.pet.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PetService {
	
	private PetDAO_interface dao;
	
	public PetService(){
		dao = new PetJDBCDAO();
	}
	
	public PetVO insertPet(
		Integer pet_status,
		String mem_no,
		String pet_type,
		String pet_breed,
		String pet_name,
		Double pet_weight,
		Date pet_birth,
		byte[] pet_img
	){
		PetVO petVO = new PetVO();
		
		petVO.setPet_status(pet_status);
		petVO.setMem_no(mem_no);
		petVO.setPet_type(pet_type);
		petVO.setPet_breed(pet_breed);
		petVO.setPet_name(pet_name);
		petVO.setPet_weight(pet_weight);
		petVO.setPet_birth(pet_birth);
		petVO.setPet_img(pet_img);
		
		dao.insert(petVO);
		
		return petVO;
	}
	
	public PetVO updatePet(
		String pet_no,
		Integer pet_status,
		String mem_no,
		String pet_type,
		String pet_breed,
		String pet_name,
		Double pet_weight,
		Date pet_birth,
		byte[] pet_img
	){
		PetVO petVO = new PetVO();
		
		petVO.setPet_no(pet_no);
		petVO.setPet_status(pet_status);
		petVO.setMem_no(mem_no);
		petVO.setPet_type(pet_type);
		petVO.setPet_breed(pet_breed);
		petVO.setPet_name(pet_name);
		petVO.setPet_weight(pet_weight);
		petVO.setPet_birth(pet_birth);
		petVO.setPet_img(pet_img);
		
		dao.update(petVO);
		
		return petVO;
	}
	
	public PetVO getOnePet(String pet_no){
		return dao.findByPrimaryKey(pet_no);
	}
	
	public List<PetVO> getAll(){
		return dao.getAll();
	}
	
}