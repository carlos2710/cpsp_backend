package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.UbicacionResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ubicaciones")
@Tag(name = "Ubicaciones", description = "Catálogos de departamentos, provincias y distritos")
public class UbicacionController {
    private final JdbcTemplate jdbcTemplate;

    public UbicacionController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/departamentos")
    @Operation(summary = "Listar departamentos")
    public List<UbicacionResponse> listarDepartamentos() {
        return jdbcTemplate.query("""
                SELECT dep_codigo AS codigo, dep_descripcion AS descripcion
                FROM t_departamento
                ORDER BY dep_descripcion
                """, (rs, rowNum) -> new UbicacionResponse(
                rs.getInt("codigo"), rs.getString("descripcion")
        ));
    }

    @GetMapping("/departamentos/{codigoDepartamento}/provincias")
    @Operation(summary = "Listar provincias por departamento")
    public List<UbicacionResponse> listarProvincias(@PathVariable Integer codigoDepartamento) {
        return jdbcTemplate.query("""
                SELECT pro_codigo AS codigo, pro_descripcion AS descripcion
                FROM t_provincia
                WHERE pro_coddepartmento = ?
                ORDER BY pro_descripcion
                """, (rs, rowNum) -> new UbicacionResponse(
                rs.getInt("codigo"), rs.getString("descripcion")
        ), codigoDepartamento);
    }

    @GetMapping("/provincias/{codigoProvincia}/distritos")
    @Operation(summary = "Listar distritos por provincia")
    public List<UbicacionResponse> listarDistritos(@PathVariable Integer codigoProvincia) {
        return jdbcTemplate.query("""
                SELECT dis_codigo AS codigo, dis_descripcion AS descripcion
                FROM t_distrito
                WHERE dis_codprovincia = ?
                ORDER BY dis_descripcion
                """, (rs, rowNum) -> new UbicacionResponse(
                rs.getInt("codigo"), rs.getString("descripcion")
        ), codigoProvincia);
    }
}
