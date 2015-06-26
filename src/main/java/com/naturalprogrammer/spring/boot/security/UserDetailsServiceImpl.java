package com.naturalprogrammer.spring.boot.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naturalprogrammer.spring.boot.domain.AbstractUser;
import com.naturalprogrammer.spring.boot.domain.AbstractUserRepository;

@Service
class UserDetailsServiceImpl
	<U extends AbstractUser<U,ID>, ID extends Serializable>
implements UserDetailsService {

    @Autowired
	private AbstractUserRepository<U,ID> userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		
		U user = userRepository.findByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException(email);

		return user;
		//return new UserDetailsImpl<U,ID>(user);

	}

}
