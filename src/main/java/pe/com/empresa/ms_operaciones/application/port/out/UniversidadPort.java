package pe.com.empresa.ms_operaciones.application.port.out;

import pe.com.empresa.ms_operaciones.domain.model.Universidad;

import java.util.List;

public interface UniversidadPort {
    List<Universidad> listar();
    Integer registrar(String nombre);
}
