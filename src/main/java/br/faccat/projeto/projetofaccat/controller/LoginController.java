/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.controller;

import br.faccat.projeto.projetofaccat.dto.UserDTO;
import br.faccat.projeto.projetofaccat.model.Role;
import br.faccat.projeto.projetofaccat.model.User;
import br.faccat.projeto.projetofaccat.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javassist.NotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.http.HttpStatus;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author tim
 */
@RestController
@Api(value = "Rest API - Contribuintes")
public class LoginController {
    
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(value = "/users")
    @ApiOperation(value = "Retorna uma lista com todos os usuarios cadastrados no banco de dados")
    public ResponseEntity<List<UserDTO>> getUsers(){
       List<UserDTO> dtos= userService.getUsers().stream().map(user->new UserDTO(user)).collect(Collectors.toList());
       return ResponseEntity.ok().body(dtos);
    }
    @GetMapping(value = "/users/{login}")
    @ApiOperation(value = "Retorna uma lista com todos os usuarios cadastrados no banco de dados")
    public ResponseEntity<UserDTO> getUser(@PathVariable String login){
       UserDTO dtos= Optional.of(userService.getUser(login)).map(user->new UserDTO(user)).orElse(null);
       return ResponseEntity.ok().body(dtos);
    }
    @PostMapping(value = "/signin")
    @ApiOperation(value = "Adiciona um usuario no banco de dados")
    public ResponseEntity<UserDTO> saveUser(@RequestBody User user){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(new UserDTO(userService.saveUser(user)));
    }

    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/role")
    @ApiOperation(value = "Adiciona uma role nova no banco de dados")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/role/addtouser")
    @ApiOperation(value = "Vincula uma role a um usuario")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
    @ApiOperation(value = "Solicita um novo token a partir do refresh token")
    @GetMapping(value = "/token/refreshtoken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
            Long token_exp=System.currentTimeMillis()+(Integer.valueOf(30)*1*1000);
            
            String refresh_token=request.getHeader("Authorization");
            String token=refresh_token.split("Bearer")[1].trim();
            
            
            if(refresh_token !=null){
                try{
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier=JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    
                    String username=decodedJWT.getSubject();
                    String isRefreshToken=decodedJWT.getClaim("refreshToken").as(String.class);
                    if(!Boolean.parseBoolean(isRefreshToken)){
                        throw new RuntimeException("Forbbiden revalidate token with normal token");
                    }
                    User user=userService.getUser(username);
                    String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(token_exp))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                   
                    Map<String, String> tokens=new HashMap<>();
                    tokens.put("access_token", access_token);
                    tokens.put("access_token_expiration",String.valueOf(token_exp));
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                    
                }catch(Exception e){
                    response.setHeader("error", e.getMessage());
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    Map<String, String> erros=new HashMap<>();
                    erros.put("error_message", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), erros);
                }
            }else{
                throw new RuntimeException("Refresh token is missing");
            }
    }
    
    
}

class RoleToUserForm{
        private String username;
        private String roleName;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
        
        
    }
