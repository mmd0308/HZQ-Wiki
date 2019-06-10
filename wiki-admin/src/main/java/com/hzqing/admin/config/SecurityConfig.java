package com.hzqing.admin.config;


import com.hzqing.admin.filter.JWTAuthFilter;
import com.hzqing.admin.sercurity.HZQAccessDeniedHandler;
import com.hzqing.admin.sercurity.HZQAuthenticationFailure;
import com.hzqing.admin.sercurity.HZQAuthenticationSuccess;
import com.hzqing.admin.sercurity.service.UserInfoDetailsService;
import com.hzqing.admin.service.system.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Spring Security 配置文件
 * @author hzqing
 * @date 2018/10/27 14:37
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserInfoDetailsService userInfoDetailsService;

    @Autowired
    private HZQAuthenticationSuccess authenticationSuccess;

    @Autowired
    private HZQAuthenticationFailure authenticationFailure;


    @Autowired
    private IAuthService authService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userInfoDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //关闭跨站请求防护
                .headers().frameOptions().disable() //允许网页iframe
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/auth/login_error").permitAll()
                .antMatchers("/fs/**").permitAll()
                .antMatchers("/user/register").permitAll() //注册用户接口放行
                .antMatchers("/druid/**").permitAll() // 放行所有druid资源
                .antMatchers("/**").authenticated()
                .and()
                //前后端分离采用JWT 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/auth/loginError")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccess)
                .failureHandler(authenticationFailure)
                .and()
                .addFilter(new JWTAuthFilter(authenticationManager(),userInfoDetailsService,authService))
                // 配置没有权限返回的数据
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
        ;
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return  new HZQAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
