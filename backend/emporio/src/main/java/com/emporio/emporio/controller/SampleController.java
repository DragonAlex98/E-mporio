package com.emporio.emporio.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
  /*
   * Esempio di endpoint per cui e'possibile accedere anche senza token
   */
  @RequestMapping(value = "public/hello", method = RequestMethod.GET)
  public @ResponseBody String publicHelloWorld() {
    return "Ciao Mondo-Pubblico";
  }

  /*
   * Esempio di endpoint per cui e'possibile accedere solo con token valido
   */
  @RequestMapping(value = "protected/hello", method = RequestMethod.GET)
  public @ResponseBody String protectedHelloWorld() {
    return "Ciao Mondo-Protetto";
  }

  @SuppressWarnings("rawtypes")
  @GetMapping("/me")
  public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
    Map<Object, Object> model = new HashMap<>();
    model.put("username", userDetails.getUsername());
    model.put("roles", userDetails.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority())
        .collect(Collectors.toList()));

    return ResponseEntity.ok(model);
  }
}