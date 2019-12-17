package com.cloud.health.authorizationservice.service;



import com.cloud.health.authorizationservice.model.AuthUserDetail;
import com.cloud.health.authorizationservice.model.User;
import com.cloud.health.authorizationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userDetailRepository;

//	@Override
//	public UserDetails loadUserByUsername(String input) {
//		User user = userRepository.findByUsername(input);
//
//		if (user == null)
//			throw new BadCredentialsException("Bad credentials");
//
//		new AccountStatusUserDetailsChecker().check(user);
//
//		return user;
//	}

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDetailRepository.findByUsername(name);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password id wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());

        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;
    }
}
