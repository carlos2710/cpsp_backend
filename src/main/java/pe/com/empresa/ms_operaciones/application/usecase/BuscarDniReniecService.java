package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.stereotype.Service;
import pe.com.empresa.ms_operaciones.application.port.in.BuscarDniReniecUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.ConsultarDniReniecPort;
import pe.com.empresa.ms_operaciones.domain.model.DniReniecConsulta;

@Service
public class BuscarDniReniecService implements BuscarDniReniecUseCase {
    private final ConsultarDniReniecPort consultarDniReniecPort;

    public BuscarDniReniecService(ConsultarDniReniecPort consultarDniReniecPort) {
        this.consultarDniReniecPort = consultarDniReniecPort;
    }

    @Override
    public DniReniecConsulta buscar(String dni) {
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("El DNI debe contener exactamente 8 dígitos.");
        }
        return consultarDniReniecPort.consultar(dni);
    }
}
