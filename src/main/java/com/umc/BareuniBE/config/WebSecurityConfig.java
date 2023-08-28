package com.umc.BareuniBE.config;

import com.umc.BareuniBE.config.security.JwtAuthenticationFilter;
import com.umc.BareuniBE.config.security.JwtTokenProvider;
import com.umc.BareuniBE.config.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.umc.BareuniBE.config.security.oauth2.UserOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// Spring security 사용하는 경우 Postman 의 접근을 security 가 막기 때문에 설정해주어야 함.
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final UserOAuth2Service userOAuth2Service;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제
                .csrf().disable()// csrf 보안 토큰 disable
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session 사용 X
                .and()
                .authorizeRequests()
                //.antMatchers("/users/test").hasRole("USER")
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll()
                //kakao로그인
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/login-success")
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .userInfoEndpoint()
                .userService(userOAuth2Service);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests();
    }
}
