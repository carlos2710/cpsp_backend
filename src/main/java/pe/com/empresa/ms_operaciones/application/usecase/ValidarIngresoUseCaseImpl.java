package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.com.empresa.ms_operaciones.application.port.in.ValidarIngresoUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.AccesoRepositoryPort;
import pe.com.empresa.ms_operaciones.domain.model.UsuarioAcceso;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request.AccesoRequest;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request.UsuarioRequest;

import java.util.Optional;

@Service
public class ValidarIngresoUseCaseImpl implements ValidarIngresoUseCase {
    private final AccesoRepositoryPort accesoRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public  ValidarIngresoUseCaseImpl(AccesoRepositoryPort accesoRepositoryPort, PasswordEncoder passwordEncoder){
        this.accesoRepositoryPort = accesoRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioAcceso execute (AccesoRequest accesoRequest){
        if (accesoRequest.userName() == null ||
                accesoRequest.userName().isBlank() ||
                accesoRequest.password() == null ||
                accesoRequest.password().isBlank()){
            return null;
        }

        Optional<UsuarioAcceso> resultado =
                accesoRepositoryPort.validarCredenciales(accesoRequest.userName());

        if (resultado.isEmpty()){
            return null;
        }

        UsuarioAcceso usuarioAcceso = resultado.get();

        if(!Integer.valueOf(1).equals(usuarioAcceso.estado())){
            return null;
        }

        if(!Integer.valueOf(0).equals(usuarioAcceso.marca())){
            return null;
        }

        if(usuarioAcceso.passwordHash() == null ||
                !passwordEncoder.matches(accesoRequest.password(),usuarioAcceso.passwordHash())){
            return null;
        }


        return new UsuarioAcceso(
                usuarioAcceso.idUsuario(),
                usuarioAcceso.userName(),
                null,
                usuarioAcceso.estado(),
                usuarioAcceso.marca(),
                usuarioAcceso.codLocal(),
                usuarioAcceso.codEmpresa(),
                usuarioAcceso.codPerfil()
        );
    }
}
