/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.filter;

import br.faccat.projeto.projetofaccat.exceptions.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.http.HttpStatus;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 *
 * @author tim
 */
@Configuration
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter{

    private final HandlerExceptionResolver handlerExceptionResolver;
    public CustomAuthorizationFilter(HandlerExceptionResolver handlerExceptionResolver){
        this.handlerExceptionResolver=handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      
        if(request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refreshtoken")){
            filterChain.doFilter(request, response);
        }else{
            String authorizationHeader=request.getHeader(AUTHORIZATION);
            if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier=JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username=decodedJWT.getSubject();
                    String isRefreshToken=decodedJWT.getClaim("refreshToken").as(String.class);
                    if(Boolean.parseBoolean(isRefreshToken)){
                        throw new RuntimeException("Forbbiden access with refresh token");
                    }
                    
                    String[] roles= decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
                    
                    stream(roles).forEach(role->authorities.add(new SimpleGrantedAuthority(role)));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null, authorities);
                    
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    
                    filterChain.doFilter(request, response);
                    
                }catch(SignatureVerificationException e){
                    log.error("Error loging in: {}",e.getMessage());
                    handlerExceptionResolver.resolveException(request,response,null,new InvalidTokenException("The token is not válid"));

                }
                catch(JWTDecodeException e){
                    handlerExceptionResolver.resolveException(request,response,null,new InvalidTokenException("The token is not a valid string"));
                }
                catch(Exception e){
                    handlerExceptionResolver.resolveException(request,response,null,new InvalidTokenException("Error validating the token"));
                }
            }else{
                filterChain.doFilter(request, response);
            }
        }
    }
   
    
}
