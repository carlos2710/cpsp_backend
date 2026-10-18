package pe.com.empresa.ms_operaciones.application.port.out;

import pe.com.empresa.ms_operaciones.domain.model.Ubigeo;

import java.util.List;

public interface UbigeoPort {
    List<Ubigeo> listar();
}
