package pe.com.empresa.ms_operaciones.application.port.out;

import pe.com.empresa.ms_operaciones.domain.model.DniReniecConsulta;

public interface ConsultarDniReniecPort {
    DniReniecConsulta consultar(String dni);
}
