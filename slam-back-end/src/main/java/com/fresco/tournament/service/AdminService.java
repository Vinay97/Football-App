
package com.fresco.tournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import static java.util.Collections.emptyList;

import com.fresco.tournament.models.Admin;
import com.fresco.tournament.repo.AdminRepository;

@Service
public class AdminService implements UserDetailsService{
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	BCryptPasswordEncoder bEncoder;
	
	public Admin addAdmin(Admin admin) {
		admin.setPassword(bEncoder.encode(admin.getPassword()));
		return this.adminRepo.save(admin);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Admin admin = adminRepo.findByname(username);
		 if (admin == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new User(admin.getName(), admin.getPassword(), emptyList());

	}
}
