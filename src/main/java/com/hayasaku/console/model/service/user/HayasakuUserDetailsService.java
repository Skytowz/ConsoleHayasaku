package com.hayasaku.console.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hayasaku.console.model.dao.HayasakuUserRepository;
import com.hayasaku.console.model.dto.HayasakuUser;

public class HayasakuUserDetailsService implements UserDetailsService {

	@Autowired
	private HayasakuUserRepository HayasakuUserRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	if("PnPMonitoring6".equals(username)) throw new UsernameNotFoundException("Not a real User");
    	HayasakuUser user = HayasakuUserRepository.findByUsername(username).orElse(null);
        if(user == null) user = HayasakuUserRepository.findByEmail(username).orElse(null);
        if (user == null) throw new UsernameNotFoundException("Could not find user");
        return user;
    }
}
