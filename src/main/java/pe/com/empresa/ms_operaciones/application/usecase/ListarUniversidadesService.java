package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.empresa.ms_operaciones.application.port.in.ListarUniversidadesUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.UniversidadPort;
import pe.com.empresa.ms_operaciones.domain.model.Universidad;

import java.util.List;

@Service
public class ListarUniversidadesService implements ListarUniversidadesUseCase {
    private final UniversidadPort universidadPort;

    public ListarUniversidadesService(UniversidadPort universidadPort) {
        this.universidadPort = universidadPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Universidad> execute() {
        return universidadPort.listar();
    }
}
