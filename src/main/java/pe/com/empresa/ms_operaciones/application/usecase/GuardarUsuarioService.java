package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.empresa.ms_operaciones.application.port.in.GuardarUsuarioUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.UsuarioPort;
import pe.com.empresa.ms_operaciones.domain.model.GuardarUsuario;

import java.util.List;

@Service
public class GuardarUsuarioService implements GuardarUsuarioUseCase {
    private final UsuarioPort usuarioPort;
    private final PasswordEncoder passwordEncoder;

    public GuardarUsuarioService(UsuarioPort usuarioPort, PasswordEncoder passwordEncoder) {
        this.usuarioPort = usuarioPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Integer execute(GuardarUsuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Los datos del usuario son obligatorios.");
        }
        if (usuario.locales() == null || usuario.locales().isEmpty()) {
            throw new IllegalArgumentException("Debe asignar al menos un local al usuario.");
        }
        if (usuario.codigoPerfil() == null || usuario.codigoNatural() == null
                || usuario.codigoPersona() == null || usuario.tipoUsuario() == null) {
            throw new IllegalArgumentException("Los códigos de perfil, natural, persona y tipo de usuario son obligatorios.");
        }
        if (usuario.usuario() == null || usuario.usuario().isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio.");
        }
        if (usuario.clave() == null || usuario.clave().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }
        if (usuario.codigoUsuario() != null && usuario.codigoUsuario() <= 0) {
            throw new IllegalArgumentException("El código del usuario debe ser mayor que cero.");
        }

        if (usuario.locales().stream().anyMatch(local -> local == null || local <= 0)) {
            throw new IllegalArgumentException("Los códigos de local deben ser mayores que cero.");
        }
        List<Integer> locales = List.copyOf(usuario.locales());

        GuardarUsuario usuarioConClaveCifrada = new GuardarUsuario(
                usuario.codigoUsuario(),
                locales,
                usuario.codigoPerfil(),
                usuario.codigoNatural(),
                usuario.codigoPersona(),
                usuario.tipoUsuario(),
                usuario.usuario().trim(),
                passwordEncoder.encode(usuario.clave()),
                usuario.estado() == null ? 1 : usuario.estado(),
                usuario.marca() == null ? 0 : usuario.marca(),
                usuario.usuarioRegistro(),
                usuario.marcaDetalle() == null ? 1 : usuario.marcaDetalle(),
                usuario.telefono(),
                usuario.email(),
                usuario.codigoEmpresa()
        );

        return usuarioPort.guardar(usuarioConClaveCifrada);
    }
}
