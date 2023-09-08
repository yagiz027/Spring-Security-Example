package com.yagiz.SpringSecurityExample.commons.constans.Security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yagiz.SpringSecurityExample.Repository.UserRepository;
import com.yagiz.SpringSecurityExample.commons.RestException.utils.constants.Messages;
import com.yagiz.SpringSecurityExample.commons.RestException.utils.exceptions.BusinessException;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceConfiguration {

    private final UserRepository userRepository;

    /**
     * Kullanıcı tarafından girilmiş olan User bilgilerini {@link UserRepository}'den alarak, UserDetailsService {@link UserDetailsService} interface'i üzeriden erişilebiecek hale getirir.
     * @return {@link UserDetailsService}
     * @throws {@link BusinessException}
     * @author {@link http://www.github.com/yagiz027} : yagizeris
     */
    
    @Bean
    public UserDetailsService userDetailsService(){
        return username-> userRepository.findByUserEmail(username)
        .orElseThrow(()-> new BusinessException(Messages.User.UserNotFound));
    }

    /** 
     * {@link UserDetailsService} implementation'ınını kullanarak bir {@link DaoAuthenticationProvider} döndürür.
     * @return {@link DaoAuthenticationProvider} class
     * @author {@link http://www.github.com/yagiz027} : yagizeris
    */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());   
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    /**
     * @param config {@link AuthenticationConfiguration} nesnesi alarak bir {@link AuthenticationManager} interface'i döndürür.
     * @return {@link AuthenticationManager} 
     * @throws Exception 
     * @author {@link http://www.github.com/yagiz027} : yagizeris
     */

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
 
    /**
     * Şifrelenmiş olan "password" bilgisinin decode edilerek döndürülmesini sağlar.
     * @return {@link BCryptPasswordEncoder} class
     * @author {@link http://www.github.com/yagiz027} : yagizeris
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
