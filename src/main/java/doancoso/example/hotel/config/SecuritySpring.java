package doancoso.example.hotel.config;

import doancoso.example.hotel.jwt.JwtAuthenticationFilter;
import doancoso.example.hotel.service.ICustomUserDetailsService;
import doancoso.example.hotel.service.impl.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class SecuritySpring  {
    @Autowired
    ICustomUserDetailsService customUserDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeHttpRequests().requestMatchers("/api/v1/user/*","api/v1/home/**","api/v1/ShoppingCart/**","api/v1/admin/Province","api/v1/admin/Hotel","api/v1/admin/Room","api/v1/admin/Room/Search","api/v1/admin/Hotel/Search","api/v1/admin/User/Search","api/v1/admin/RoomEdit/**","api/v1/admin/ProvinceEdit/**","api/v1/admin/HotelEdit/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore( jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("message","Unauthorized");
                response.getWriter().write("Unauthorized");
            }
        }).accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setHeader("message","Access Denied!");
                response.getWriter().write("Access Denied!");
            }
        });

        return http.build();
    }
}
