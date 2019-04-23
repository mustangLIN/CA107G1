package com.pet.model;

import java.util.*;

public interface PetDAO_interface {
	public void insert(PetVO petVO);
	public void update(PetVO petVO);
	public PetVO findByPrimaryKey(String pet_no);
	public List<PetVO> getAll();
	public List<PetVO> getPetVO();
	
}
