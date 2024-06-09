package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Repository.DbController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DbController dbController;

    // contrucktor injektion af dbController
    @Autowired
    public UserDetailsServiceImpl(DbController dbController) {
        this.dbController = dbController;
    }

    // Denne metode retunere en collection af authorities (Roller) for en given bruger
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    // Denne metode bliver brugt til at indlæse en bruger udfra brugernavn(Email).
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional <User> optionalUser = dbController.findByMail(username);
            if (optionalUser.isEmpty()) { //hvis brugeren ikke bliver fundet
                throw new UsernameNotFoundException("User not found");
            }

            User user = optionalUser.get(); // hvis en bruger bliver fundet, henter vi brugeren fra optional
            //Så retunere vi et UserDetails objekt med brugerens email, password og authorities
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    getAuthorities(user)
            );

        } catch (EmptyResultDataAccessException e){
            //hvis der en exception når vi prøver at finde brugeren, kaster vi en UsernameNotFoundException
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
    }
}
