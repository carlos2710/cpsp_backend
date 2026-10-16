package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response;

public record MenuPerfilResponse(
        String perfil,
        Integer codigoModulo,
        String modulo,
        Integer codigoSubmenu,
        String submenu,
        String ruta,
        Integer orden
) {

}
