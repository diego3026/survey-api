package com.survey.api.controllers;

import com.survey.api.models.dtos.auth.*;
import com.survey.api.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticacion", description = "Recurso para manejar la autenticacion")
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Actualizar ROL de usuario",
            description = "El administrador podrá actualizar el rol del usuario",
            tags = { "Put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PutMapping("/{idAdmin}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private ResponseEntity<?> updateRole(@PathVariable Long idAdmin, @RequestBody UpdateRole updateRole){
        try{
            authService.updateRole(idAdmin,updateRole);
            return ResponseEntity.ok("Role updated");
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(
            summary = "registrar de usuario",
            description = "El usuario podrá registrarse en el sistema",
            tags = { "Post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseAuth.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PostMapping("/register")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @Operation(
            summary = "login de usuario",
            description = "El usuario podrá loguearse en el sistema",
            tags = { "Post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseAuth.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PostMapping("/login")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Operation(
            summary = "refresh de usuario",
            description = "El sistema podrá refrescar el token de acceso",
            tags = { "Post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TokenResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PostMapping("/refresh")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private ResponseEntity<?> refresh(@RequestBody String refresh){
        try{
            return ResponseEntity.ok(authService.refreshToken(refresh));
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
