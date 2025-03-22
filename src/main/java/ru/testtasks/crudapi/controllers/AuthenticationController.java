package ru.testtasks.crudapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.testtasks.crudapi.configuration.JwtToken;

@RestController
@Tag(name = "Аутентификация")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final UserDetailsService userDetailsService;

  private final JwtToken jwtTokenUtil;

  @PostMapping("/authenticate")
  @Operation(summary = "Аутентификация и получения токена")
  public ResponseEntity<String> createAuthenticationToken(
      @RequestParam String userName,
      @RequestParam String password
  ) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userName, password)
    );

    final UserDetails userDetails = userDetailsService.loadUserByUsername(
        userName);
    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(jwt);
  }

}
