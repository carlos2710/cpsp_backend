package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest (
        Integer idUsuario,

        @NotNull
        Integer tipoDocumento,

        @NotBlank
        String numeroDocumento,

        @NotBlank
        String apellidoPaterno,

        @NotBlank
        String apellidoMaterno,

        @NotBlank
        String primerNombre,

        String segundoNombre,

        String telefono,

        @Email
        String email,

        @NotNull
        Long local,

        @NotNull
        Long perfil,

        @NotNull
        Integer tipoUsuario,

        @NotBlank
        String usuario,

        @NotBlank
        String clave
){

}
