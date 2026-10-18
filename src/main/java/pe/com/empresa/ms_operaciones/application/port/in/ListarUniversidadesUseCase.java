package pe.com.empresa.ms_operaciones.application.port.in;

import pe.com.empresa.ms_operaciones.domain.model.Universidad;

import java.util.List;

public interface ListarUniversidadesUseCase {
    List<Universidad> execute();
}
