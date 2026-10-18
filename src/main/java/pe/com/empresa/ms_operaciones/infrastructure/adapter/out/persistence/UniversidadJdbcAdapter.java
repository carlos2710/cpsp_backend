package pe.com.empresa.ms_operaciones.infrastructure.adapter.out.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.empresa.ms_operaciones.application.port.out.UniversidadPort;
import pe.com.empresa.ms_operaciones.domain.model.Universidad;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UniversidadJdbcAdapter implements UniversidadPort {
    private final JdbcTemplate jdbcTemplate;

    public UniversidadJdbcAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Universidad> listar() {
        String sql = """
                EXEC dbo.usp_t_universidad_listar
                """;

        List<Universidad> universidades = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> mapearUniversidad(rs)
        );

        return universidades;
    }

    @Override
    public Integer registrar(String nombre) {
        String sql = """
                EXEC dbo.usp_t_universidad_registrar
                    @uni_nombre = ?
                """;

        List<Integer> codigos = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getInt("uni_codigo"),
                nombre
        );

        return codigos.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "El SP usp_t_universidad_registrar no devolvió el código de la universidad creada."));
    }

    private Universidad mapearUniversidad(ResultSet rs) throws SQLException {
        return new Universidad(
                obtenerEntero(rs, "uni_codigo", "codigo", "codigo_universidad"),
                obtenerTexto(rs, "uni_nombre", "nombre", "nombre_universidad")
        );
    }

    private Integer obtenerEntero(ResultSet rs, String... nombres) throws SQLException {
        return rs.getInt(buscarColumna(rs, nombres));
    }

    private String obtenerTexto(ResultSet rs, String... nombres) throws SQLException {
        return rs.getString(buscarColumna(rs, nombres));
    }

    private String buscarColumna(ResultSet rs, String... nombres) throws SQLException {
        ResultSetMetaData metadata = rs.getMetaData();
        for (int i = 1; i <= metadata.getColumnCount(); i++) {
            String label = metadata.getColumnLabel(i);
            for (String nombre : nombres) {
                if (label.equalsIgnoreCase(nombre)) {
                    return label;
                }
            }
        }
        throw new SQLException("El SP usp_t_universidad_listar no devolvió columnas compatibles: "
                + String.join(", ", nombres));
    }
}
