package com.survey.api.controllers;

import com.survey.api.models.dtos.save.UserUpdate;
import com.survey.api.models.dtos.send.UserResponse;
import com.survey.api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuarios", description = "Recurso para manejar los usuarios")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "obtener todas los usuarios",
            description = "El administrador podrá obtener todas los usuarios",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(
            summary = "obtener el usuario por username",
            description = "El administrador podrá obtener el usuario por username",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping("/{username}/username")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @Operation(
            summary = "obtener el usuario por email",
            description = "El administrador podrá obtener el usuario por email",
            tags = { "Get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @GetMapping("/{email}/email")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @Operation(
            summary = "actualizar el usuario",
            description = "El administrador o usuario podrá actualizar el usuario por Id",
            tags = { "Put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserUpdate userUpdate){
        return ResponseEntity.ok(userService.update(id,userUpdate));
    }

    @Operation(
            summary = "eliminar el usuario",
            description = "El administrador podrá eliminar el usuario por Id",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }

    @Operation(
            summary = "eliminar todos los usuarios",
            description = "El administrador podrá eliminar todos los usuarios",
            tags = { "Delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") })})
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> deleteAll(){
        userService.deleteAll();
        return ResponseEntity.ok("Users deleted");
    }
}
