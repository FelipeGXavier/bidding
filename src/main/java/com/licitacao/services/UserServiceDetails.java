package com.licitacao.services;

import com.licitacao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.licitacao.domain.User user = Optional.ofNullable(this.userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        return new User(user.getUsername(), user.getPassword(), user.isAdmin() ? authorityListAdmin : authorityListUser);
    }
}
