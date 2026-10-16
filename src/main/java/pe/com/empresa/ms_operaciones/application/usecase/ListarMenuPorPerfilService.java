package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.stereotype.Service;
import pe.com.empresa.ms_operaciones.application.port.in.ListarMenuPorPerfilUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.MenuPerfilPort;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.MenuPerfilResponse;

import java.awt.*;
import java.util.List;

@Service
public class ListarMenuPorPerfilService implements ListarMenuPorPerfilUseCase {

    private final MenuPerfilPort menuPerfilPort;

    public ListarMenuPorPerfilService(MenuPerfilPort menuPerfilPort){
        this.menuPerfilPort = menuPerfilPort;
    }

    @Override
    public List<MenuPerfilResponse> ejecutar(Integer codPerfil) {
        if(codPerfil == null || codPerfil <= 0){
            throw new IllegalArgumentException(
                    "El código del perfil debe ser mayor que cero"
            );
        }
        return menuPerfilPort.listarPorPerfil(codPerfil);
    }
}
