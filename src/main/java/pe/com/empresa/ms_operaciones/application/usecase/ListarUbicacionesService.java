package pe.com.empresa.ms_operaciones.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.empresa.ms_operaciones.application.port.in.ListarUbicacionesUseCase;
import pe.com.empresa.ms_operaciones.application.port.out.UbigeoPort;
import pe.com.empresa.ms_operaciones.domain.model.Ubigeo;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.UbicacionResponse;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ListarUbicacionesService implements ListarUbicacionesUseCase {
    private final UbigeoPort ubigeoPort;

    public ListarUbicacionesService(UbigeoPort ubigeoPort) {
        this.ubigeoPort = ubigeoPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UbicacionResponse> listarDepartamentos() {
        Map<Integer, UbicacionResponse> departamentos = new LinkedHashMap<>();
        listarUbigeo().forEach(ubigeo -> departamentos.putIfAbsent(
                ubigeo.codigoDepartamento(),
                new UbicacionResponse(ubigeo.codigoDepartamento(), ubigeo.departamento())
        ));
        return ordenar(departamentos.values().stream().toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UbicacionResponse> listarProvincias(Integer codigoDepartamento) {
        Map<Integer, UbicacionResponse> provincias = new LinkedHashMap<>();
        listarUbigeo().stream()
                .filter(ubigeo -> ubigeo.codigoDepartamento().equals(codigoDepartamento))
                .forEach(ubigeo -> provincias.putIfAbsent(
                        ubigeo.codigoProvincia(),
                        new UbicacionResponse(ubigeo.codigoProvincia(), ubigeo.provincia())
                ));
        return ordenar(provincias.values().stream().toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UbicacionResponse> listarDistritos(Integer codigoProvincia) {
        Map<Integer, UbicacionResponse> distritos = new LinkedHashMap<>();
        listarUbigeo().stream()
                .filter(ubigeo -> ubigeo.codigoProvincia().equals(codigoProvincia))
                .forEach(ubigeo -> distritos.putIfAbsent(
                        ubigeo.codigoDistrito(),
                        new UbicacionResponse(ubigeo.codigoDistrito(), ubigeo.distrito())
                ));
        return ordenar(distritos.values().stream().toList());
    }

    private List<Ubigeo> listarUbigeo() {
        return ubigeoPort.listar();
    }

    private List<UbicacionResponse> ordenar(List<UbicacionResponse> ubicaciones) {
        return ubicaciones.stream()
                .sorted(Comparator.comparing(UbicacionResponse::descripcion,
                        Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
                .toList();
    }
}
