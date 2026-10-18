package pe.com.empresa.ms_operaciones.application.port.in;

import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.UbicacionResponse;

import java.util.List;

public interface ListarUbicacionesUseCase {
    List<UbicacionResponse> listarDepartamentos();
    List<UbicacionResponse> listarProvincias(Integer codigoDepartamento);
    List<UbicacionResponse> listarDistritos(Integer codigoProvincia);
}
