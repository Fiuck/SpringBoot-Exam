package top.lemcoo.exam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sun.security.util.SecurityConstants;
import top.lemcoo.exam.service.MyUserDetailsService;

import java.util.Arrays;

import static java.util.Collections.singletonList;

/**
 * 【security配置】
 *
 * @Author: zhaowx
 * @Date: 2021/6/24 21:24
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private top.lemcoo.exam.security.MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private top.lemcoo.exam.security.MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    top.lemcoo.exam.security.JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    MyAuthenticationLogoutSuccessHandler myAuthenticationLogoutSuccessHandler;

    @Value("${jwt.route.authPath}")
    private String authPath;
    @Value("${jwt.header}")
    private String jwtHeader;

    /**
     * 密码加密策略
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  在 UsernamePasswordAuthenticationFilter 之前添加 JwtAuthenticationTokenFilter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable()
                // 关闭session会话管理
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(authPath).permitAll()
                .anyRequest().authenticated()

                // 登录
                .and().formLogin().loginProcessingUrl("/auth/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)

                // 注销
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(myAuthenticationLogoutSuccessHandler)
                .and().headers().cacheControl();

        // 处理异常情况：认证失败和权限不足
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.cors(Customizer.withDefaults());
    }

    /**
     * Cors配置优化
     **/
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(singletonList("*"));
        // configuration.setAllowedOriginPatterns(singletonList("*"));
        configuration.setAllowedHeaders(singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setExposedHeaders(singletonList(jwtHeader));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
