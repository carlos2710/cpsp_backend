package pe.com.empresa.ms_operaciones.application.port.in;

import pe.com.empresa.ms_operaciones.domain.model.Local;

import java.util.List;

public interface ListarLocalesUseCase {
    List<Local> execute();
}
