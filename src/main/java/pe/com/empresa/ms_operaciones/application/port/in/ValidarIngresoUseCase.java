package pe.com.empresa.ms_operaciones.application.port.in;

import pe.com.empresa.ms_operaciones.domain.model.UsuarioAcceso;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request.AccesoRequest;

import java.util.Optional;

public interface ValidarIngresoUseCase {
    UsuarioAcceso execute(AccesoRequest request);
}
