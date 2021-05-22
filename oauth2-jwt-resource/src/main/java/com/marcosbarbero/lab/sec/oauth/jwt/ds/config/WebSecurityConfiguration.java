package com.marcosbarbero.lab.sec.oauth.jwt.ds.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import static com.google.common.base.Preconditions.checkNotNull;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final String clientId;
    private final String clientSecret;
    private final String introspectionUrl;

    /* public void configure(WebSecurity webSecurity) throws Exception {
           webSecurity.ignoring().antMatchers("/**");
     }*/
    WebSecurityConfiguration(
            @Value("${oauth.clientId}") final String clientId,
            @Value("${oauth.clientSecret}") final String clientSecret,
            @Value("${oauth.introspectionUrl}") final String introspectionUrl) {
        this.clientId = checkNotNull(clientId);
        this.clientSecret = checkNotNull(clientSecret);
        this.introspectionUrl = checkNotNull(introspectionUrl);
    }


    public void configure(WebSecurity webSecurity) throws Exception {
        //  webSecurity.ignoring().antMatchers("/**");
        webSecurity
                .ignoring()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/**l",
                        "/webjars/**",
                        // -- Swagger UI v3 (OpenAPI)
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "user/tokin"
                );
    }

    @Override //https://gitlab.com/blogging4t/resource-server/-/blob/master/src/main/java/ro/cpatrut/auth/resourceserver/config/SecurityResourceConfig.java
    public void configure(final HttpSecurity http) throws Exception {
       http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "protected-resource").hasAnyAuthority("SCOPE_read")
              .antMatchers(HttpMethod.POST, "protected-resource").hasRole("TEACHER")
                .antMatchers(HttpMethod.PUT, "protected-resource/api/student/add").hasRole("ADMIN")
      /*  http.authorizeRequests(authz -> authz
                .antMatchers(HttpMethod.GET, "/protected-resource").hasAnyAuthority("read")
                .antMatchers(HttpMethod.POST, "/protected-resource/api/student").hasRole("ADMIN")
                .anyRequest().permitAll())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .opaqueToken(token -> token.introspectionUri(introspectionUrl)
                                .introspectionClientCredentials(clientId, clientSecret)
                        )
                );*/




                /*.and()
                .oauth2ResourceServer().bearerTokenResolver()
                        .opaqueToken(token -> token.introspectionUri(introspectionUrl)
                                .introspectionClientCredentials(clientId, clientSecret)
                        )
                )*/
        //  .oauth2ResourceServer(OAuth2ResourceServerConfigurer::opaqueToken);
        ;

}
 }