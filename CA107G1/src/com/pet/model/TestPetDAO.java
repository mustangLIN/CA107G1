package com.pet.model;

import java.sql.Date;
import java.time.LocalDate;

import com.pet.model.PetVO;
import com.tool.TestIMG;

public class TestPetDAO {
	public static void main(String[] args) {
		TestPetDAO test = new TestPetDAO();
		PetJDBCDAO dao = new PetJDBCDAO();
		
		// test insert() INSERT_STMT
		for (int i = 0; i < 10; i++) {
			dao.insert(test.fakePetVO());
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i%10==0) {
				System.out.println("insert "+i);
			}
		}
		
	}
	
	private PetVO fakePetVO() {
		// create fake data
		int status = (int)Math.round(Math.random());
		int num = (int)(Math.random() * 1200);
		String tmp = Integer.toString(num);
		String mem_no = "M00" + Integer.toString(10000+num).substring(1, 5); // FK, MEMBER.mem_no already has 1200 record 
		String type = "type" + tmp;
		String bree = "bree" + tmp;
		String  name = "pet" + tmp;
		double weight = ((int)(Math.random() * 100))+((double)((int)(Math.random() * 100))/100);
		Date date = Date.valueOf(LocalDate.now().minusDays(num));
		byte[] blob = new TestIMG().fakeImg(400, 300, name);
		
		
		// set petVO
		PetVO petVO = new PetVO();
		petVO.setPet_status(status);
		petVO.setMem_no(mem_no);
		petVO.setPet_type(type);
		petVO.setPet_breed(bree);
		petVO.setPet_name(name);
		petVO.setPet_weight(weight);
		petVO.setPet_birth(date);
		petVO.setPet_img(blob);
		
		return petVO;
	}
}
