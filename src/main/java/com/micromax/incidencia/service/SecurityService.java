package com.micromax.incidencia.service;

import com.micromax.incidencia.domain.entities.users.Usuario;
import com.micromax.incidencia.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service("userDetailsService")
@Transactional(readOnly=true)
public class SecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario domainUser = usuarioRepository.findUsuarioByUsernameAndHabilitado(login, true);
        if(domainUser==null)throw new UsernameNotFoundException(String.format("No se encuntra el usuario con username = %s", login));
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        User u = new User(
                domainUser.getUsername(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                Collections.singletonList(new SimpleGrantedAuthority(domainUser.getRol().getNombre()))
        );
        return u;
    }
}
