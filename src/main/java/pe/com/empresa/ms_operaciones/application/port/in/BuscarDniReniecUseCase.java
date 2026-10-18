package pe.com.empresa.ms_operaciones.application.port.in;

import pe.com.empresa.ms_operaciones.domain.model.DniReniecConsulta;

public interface BuscarDniReniecUseCase {
    DniReniecConsulta buscar(String dni);
}
