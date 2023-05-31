package com.hayasaku.console.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hayasaku.console.model.dao.PurpleUserRepository;
import com.hayasaku.console.model.dto.PurpleUser;

public class PurpleUserDetailsService implements UserDetailsService {

	@Autowired
	private PurpleUserRepository purpleUserRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	if("PnPMonitoring6".equals(username)) throw new UsernameNotFoundException("Not a real User");
    	PurpleUser user = purpleUserRepository.findByUsername(username).orElse(null);
        if(user == null) user = purpleUserRepository.findByEmail(username).orElse(null);
        if (user == null) throw new UsernameNotFoundException("Could not find user");
        return user;
    }
}
