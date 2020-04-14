package com.potato112.springdemo.conf;

import com.potato112.springdemo.security.userauthsecurity.authentication.SysRole;

import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.potato112.springdemo.web.CustomRequestCache;
import com.potato112.springdemo.web.LoginView;
import com.potato112.springdemo.web.SysPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SysUserDetailService sysUserDetailService;

    SecurityConfiguration(SysUserDetailService sysUserDetailService) {
        this.sysUserDetailService = sysUserDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authProvider());
    }

    @Bean
    public AuthenticationProvider authProvider() {

        log.info("Echo03 auth provider");

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(sysUserDetailService);
        authProvider.setPasswordEncoder(new SysPasswordEncoder());
        return authProvider;
    }

    /**
     * Forces login to access internal pages
     * Configures login page.
     */
    protected void configure(HttpSecurity http) throws Exception {

        log.info("Echo01 security login configuration");

        http.csrf().disable()
                .requestCache().requestCache(new CustomRequestCache()) // saves only not Vaadin requests
                .and().authorizeRequests() // authorize all requests
                .requestMatchers(WebSecurityService::isFrameworkInternalRequest).permitAll() // pass all internal requests
                .antMatchers("/forgot-password").permitAll()
                .anyRequest().hasAnyAuthority(SysRole.getAllRoles())
                .and()
                .formLogin().loginPage("/" + LoginView.ROUTE).permitAll()
                .loginProcessingUrl("/" + LoginView.ROUTE).failureUrl("/" + LoginView.ROUTE + "/?error")
                .and()
                .logout().logoutSuccessUrl("/" + LoginView.ROUTE);
    }

    /**
     * Bypass Spring security for explicit static resources
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        log.info("Echo02 security bypass configuration");

        web.ignoring().antMatchers(
                "/VAADIN/build/**", "/VAADIN/static/**", "/VAADIN/config/**",
                "/manifest.webmanifest",
                "*images/**",
                "/img/**",
                "/icons/**",
                "/webjars/**",
                "/img/**",
                "/sw.js",
                "**sw.js**",
                "/favicon.ico"
        );
    }
}
