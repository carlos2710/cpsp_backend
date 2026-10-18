package pe.com.empresa.ms_operaciones.infrastructure.adapter.out.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.empresa.ms_operaciones.application.port.out.UbigeoPort;
import pe.com.empresa.ms_operaciones.domain.model.Ubigeo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UbigeoJdbcAdapter implements UbigeoPort {
    private final JdbcTemplate jdbcTemplate;

    public UbigeoJdbcAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ubigeo> listar() {
        String sql = """
                EXEC dbo.usp_ubigeo_listar
                """;

        List<Ubigeo> ubicaciones = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> mapear(rs)
        );

        return ubicaciones;
    }

    private Ubigeo mapear(ResultSet rs) throws SQLException {
        return new Ubigeo(
                rs.getInt("dep_codigo"),
                rs.getString("dep_descripcion"),
                rs.getInt("pro_codigo"),
                rs.getString("pro_descripcion"),
                rs.getInt("dis_codigo"),
                rs.getString("dis_descripcion")
        );
    }
}
