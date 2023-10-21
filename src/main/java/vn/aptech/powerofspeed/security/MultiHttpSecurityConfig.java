package vn.aptech.powerofspeed.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.aptech.powerofspeed.security.api.ApiJWTAuthenticationFilter;
import vn.aptech.powerofspeed.security.api.ApiJWTAuthorizationFilter;
import vn.aptech.powerofspeed.security.form.CustomAccessDeniedHandler;
import vn.aptech.powerofspeed.security.form.CustomAuthenticationSuccessHandler;
import vn.aptech.powerofspeed.security.form.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class MultiHttpSecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private CustomUserDetailsService userDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder);
        }

        // @formatter:off
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf()
                    .disable()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                        .antMatchers("/api/v1/user/signup").permitAll()
                        .antMatchers("/forgotPassword/**").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                    .and()
                    .addFilter(new ApiJWTAuthenticationFilter(authenticationManager()))
                    .addFilter(new ApiJWTAuthorizationFilter(authenticationManager()))
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
        // @formatter:on

    }

    @Order(2)
    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

        @Autowired
        private CustomAccessDeniedHandler customAccessDeniedHandler;

        @Autowired
        private CustomUserDetailsService userDetailsService;

        private static final String[] PUBLIC_MACTCHES = {
                "/resources/**",
                "/static/**",
                "/backend/**",
                "/frontend/**",
                "/dist/**",
                "/plugins/**",
                "/css/**",
                "/fonts/**",
                "/img/**",
                "/js/**",
        };

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder);
        }

        // @formatter:off
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .cors()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll()
                    .failureUrl("/login?error=true")
                    .successHandler(customAuthenticationSuccessHandler)
                    .and()
                    .logout()
                    .permitAll()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
                    .and()
                    .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers(PUBLIC_MACTCHES);
        }
        // @formatter:on
    }
}
