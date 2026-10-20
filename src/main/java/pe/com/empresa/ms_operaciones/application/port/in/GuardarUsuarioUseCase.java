package pe.com.empresa.ms_operaciones.application.port.in;

import pe.com.empresa.ms_operaciones.domain.model.GuardarUsuario;

public interface GuardarUsuarioUseCase {
    Integer execute(GuardarUsuario usuario);
}
