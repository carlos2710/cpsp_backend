package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.empresa.ms_operaciones.application.port.in.RegistrarUniversidadUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.UniversidadPort;

@Service
public class RegistrarUniversidadService implements RegistrarUniversidadUseCase {
    private final UniversidadPort universidadPort;

    public RegistrarUniversidadService(UniversidadPort universidadPort) {
        this.universidadPort = universidadPort;
    }

    @Override
    @Transactional
    public Integer execute(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la universidad es obligatorio.");
        }
        return universidadPort.registrar(nombre.trim());
    }
}
