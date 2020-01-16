package com.emporio.emporio.security;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.emporio.emporio.dto.OrdineHistoryDto;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.StatoConsegna;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${api.path}")
    private String apiPath;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtTokenAuthenticationFilter(jwtTokenProvider);
    }

    // configurazione Cors per poter consumare le api restful con richieste ajax
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("POST, PUT, GET, OPTIONS, DELETE"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
        .csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        // non abbiamo bisogno di una sessione
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .cors().and()
        .authorizeRequests().antMatchers(
                        // HttpMethod.GET,
                        "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
        .antMatchers(apiPath + "/**").permitAll()
        .antMatchers("/app/**").permitAll()
        //.antMatchers(HttpMethod.GET, "/api/v1/products*").permitAll()
        // commentato .antMatchers(HttpMethod.POST, "/api/v1/products*").hasRole("TITOLARE")
        //.antMatchers(HttpMethod.POST, "/api/v1/products*").hasAuthority("CREATE_PRODUCT")
        .anyRequest().authenticated();

        // Filtro Custom JWT
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mm = new ModelMapper();

        Converter<StatoConsegna, String> convertStatoConsegnaToString = new Converter<StatoConsegna, String>()
        {
            public String convert(MappingContext<StatoConsegna, String> context)
            {
                // If the dog weighs more than 25, then it must be large
                if(context.getSource() == null)
                    return "NON ASSEGNATA";

                return context.getSource().toString();
            }
        };

        PropertyMap<Ordine, OrdineHistoryDto> mymap = new PropertyMap<Ordine, OrdineHistoryDto>()
        {
            protected void configure()
            {
                using(convertStatoConsegnaToString).map(source.getOrderConsegna().getStatoConsegna()).setStatoConsegna(null);;
                map(source.getOrderConsegna().getPosto().getLocker().getLockerId()).setLockerId(null);
                map(source.getOrderConsegna().getPosto().getLocker().getAddress()).setLockerAddress(null);
                map(source.getOrderConsegna().getPosto().getNomePosto()).setNomePosto(null);
            }
        };

        mm.addMappings(mymap);

        return mm;
    }

    @Bean
    public WebMvcRegistrations WebRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new RequestMappingHandlerMapping() {
                    @Override
                    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
                        Class<?> beanType = method.getDeclaringClass();
                        if (AnnotationUtils.findAnnotation(beanType, RestController.class) != null) {
                            PatternsRequestCondition apiPattern = new PatternsRequestCondition(apiPath).combine(mapping.getPatternsCondition());
                            mapping = new RequestMappingInfo(mapping.getName(), apiPattern,
                                    mapping.getMethodsCondition(), mapping.getParamsCondition(),
                                    mapping.getHeadersCondition(), mapping.getConsumesCondition(),
                                    mapping.getProducesCondition(), mapping.getCustomCondition());
                        }

                        super.registerHandlerMethod(handler, method, mapping);
                    }
                };
            }
        };
    }
}