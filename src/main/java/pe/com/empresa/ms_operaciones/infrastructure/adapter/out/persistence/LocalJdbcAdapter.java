package pe.com.empresa.ms_operaciones.infrastructure.adapter.out.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.empresa.ms_operaciones.application.port.out.LocalPort;
import pe.com.empresa.ms_operaciones.domain.model.Local;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocalJdbcAdapter implements LocalPort {
    private final JdbcTemplate jdbcTemplate;

    public LocalJdbcAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Local> listar() {
        String sql = """
                EXEC dbo.usp_local_listar
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapearLocal(rs));
    }

    private Local mapearLocal(ResultSet rs) throws SQLException {
        return new Local(
                rs.getInt("loc_codigo"),
                rs.getString("loc_descripcion")
        );
    }
}
