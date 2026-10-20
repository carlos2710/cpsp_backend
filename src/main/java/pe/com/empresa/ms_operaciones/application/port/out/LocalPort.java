package pe.com.empresa.ms_operaciones.application.port.out;

import pe.com.empresa.ms_operaciones.domain.model.Local;

import java.util.List;

public interface LocalPort {
    List<Local> listar();
}
