package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.empresa.ms_operaciones.application.port.in.ListarLocalesUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.LocalPort;
import pe.com.empresa.ms_operaciones.domain.model.Local;

import java.util.List;

@Service
public class ListarLocalesService implements ListarLocalesUseCase {
    private final LocalPort localPort;

    public ListarLocalesService(LocalPort localPort) {
        this.localPort = localPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Local> execute() {
        return localPort.listar();
    }
}
