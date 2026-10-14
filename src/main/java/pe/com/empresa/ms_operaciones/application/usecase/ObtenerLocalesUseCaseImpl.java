package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.empresa.ms_operaciones.application.port.in.ObtenerLocalesUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.AccesoRepositoryPort;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.AccesoResponse;

import java.util.List;

@Service
public class ObtenerLocalesUseCaseImpl implements ObtenerLocalesUseCase {
    private final AccesoRepositoryPort accesoRepositoryPort;
    public ObtenerLocalesUseCaseImpl(
            AccesoRepositoryPort accesoRepositoryPort
    ){
        this.accesoRepositoryPort = accesoRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccesoResponse> execute(Integer idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException(
                    "El identificador del usuario debe ser válido.");
        }

        return accesoRepositoryPort.obtenerLocales(idUsuario);
    }
}
