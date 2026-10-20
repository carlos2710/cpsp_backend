package pe.com.empresa.ms_operaciones.application.port.out;

import pe.com.empresa.ms_operaciones.domain.model.GuardarUsuario;

public interface UsuarioPort {
    Integer guardar(GuardarUsuario usuario);
}
