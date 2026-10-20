package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;

import java.util.List;

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

        Long local,

        List<Long> locales,

        @NotNull
        Long perfil,

        Long codigoEmpresa,

        @NotNull
        Integer tipoUsuario,

        @NotBlank
        String usuario,

        @NotBlank
        String clave
){

    @AssertTrue(message = "Debe asignar al menos un local.")
    public boolean isLocalSeleccionado() {
        return local != null || (locales != null && !locales.isEmpty());
    }

}
