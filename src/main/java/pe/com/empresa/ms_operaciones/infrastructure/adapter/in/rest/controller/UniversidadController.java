package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.UniversidadResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/universidades")
@Tag(name = "Universidades", description = "Catálogo de universidades")
public class UniversidadController {
    private final JdbcTemplate jdbcTemplate;

    public UniversidadController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    @Operation(summary = "Listar universidades")
    public List<UniversidadResponse> listar() {
        return jdbcTemplate.query("""
                SELECT uni_codigo AS codigo, uni_nombre AS nombre
                FROM t_universidad
                ORDER BY uni_nombre
                """, (rs, rowNum) -> new UniversidadResponse(
                rs.getInt("codigo"), rs.getString("nombre")
        ));
    }
}
