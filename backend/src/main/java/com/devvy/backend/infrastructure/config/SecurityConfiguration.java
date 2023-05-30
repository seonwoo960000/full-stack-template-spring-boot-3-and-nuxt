package com.devvy.backend.infrastructure.config;

import static com.devvy.backend.infrastructure.config.SecurityConfiguration.SECURITY_CONFIG_NAME;
import static io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import com.devvy.backend.common.enums.AuthorityType;
import com.devvy.backend.infrastructure.security.CustomOauth2UserService;
import com.devvy.backend.infrastructure.security.jwt.JWTConfigurer;
import com.devvy.backend.infrastructure.security.jwt.TokenProvider;

import io.swagger.v3.oas.annotations.security.SecurityScheme;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(securedEnabled = true)
@Import(SecurityProblemSupport.class)
@SecurityScheme(name = SECURITY_CONFIG_NAME, in = HEADER, type = HTTP, scheme = "bearer", bearerFormat = "JWT")
public class SecurityConfiguration {

    public static final String SECURITY_CONFIG_NAME = "App Bearer Token";
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final SecurityProblemSupport securityProblemSupport;
    private final CustomOauth2UserService customOauth2UserService;

    public SecurityConfiguration(TokenProvider tokenProvider,
                                 CorsFilter corsFilter,
                                 SecurityProblemSupport securityProblemSupport,
                                 CustomOauth2UserService customOauth2UserService) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.securityProblemSupport = securityProblemSupport;
        this.customOauth2UserService = customOauth2UserService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exceptionHandlerCustomizer -> exceptionHandlerCustomizer
                .authenticationEntryPoint(securityProblemSupport)
                .accessDeniedHandler(securityProblemSupport))
            .formLogin(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headersCustomizer -> headersCustomizer.frameOptions(FrameOptionsConfig::disable))
            .sessionManagement(custz -> custz.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authz ->
                authz
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/management/health").permitAll()
                    .requestMatchers("/management/health/**").permitAll()
                    .requestMatchers("/management/info").permitAll()
                    .requestMatchers("/management/**").hasAuthority(AuthorityType.ADMIN.getRole())
                    .requestMatchers("/api/v1/public/**").permitAll()
                    .requestMatchers("/api/v1/**").hasAuthority(AuthorityType.USER.getRole())
                    .anyRequest().authenticated()
            )
            .oauth2Login(authz ->
                authz.defaultSuccessUrl("/api/v1/public/oauth2/success")
                    .failureUrl("/api/v1/public/oauth2/fail")
                    .userInfoEndpoint(customizer -> customizer.userService(customOauth2UserService))
            )
            .apply(securityConfigurerAdapter());

        return http.build();
    }

    private JWTConfigurer securityConfigurerAdapter() { return new JWTConfigurer(tokenProvider);  }

}
