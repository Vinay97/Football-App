package com.fresco.tournament.controllers;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.tournament.models.Admin;
import com.fresco.tournament.repo.AdminRepository;
import com.fresco.tournament.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	AdminRepository repo;
	
	@Autowired
	BCryptPasswordEncoder encode;
	
	@RequestMapping(value="/registerAdmin",method=RequestMethod.POST)
	public void register(@RequestBody Admin adminDet){
		adminDet.setPassword(encode.encode(adminDet.getPassword()));
		repo.save(adminDet);
		//return new ResponseEntity<Admin>(adminService.addAdmin(adminDet),HttpStatus.OK);
	}
}