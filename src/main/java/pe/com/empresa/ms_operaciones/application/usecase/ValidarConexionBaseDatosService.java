package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.stereotype.Service;
import pe.com.empresa.ms_operaciones.application.port.in.ValidarConexionBaseDatosUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.ConexionBaseDatosPort;

@Service
public class ValidarConexionBaseDatosService implements ValidarConexionBaseDatosUseCase {
    private final ConexionBaseDatosPort conexionBaseDatosPort;

    public ValidarConexionBaseDatosService(ConexionBaseDatosPort conexionBaseDatosPort) {
        this.conexionBaseDatosPort = conexionBaseDatosPort;
    }

    @Override
    public boolean execute() {
        return conexionBaseDatosPort.estaDisponible();
    }
}
