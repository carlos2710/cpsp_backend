package pe.com.empresa.ms_operaciones.infrastructure.adapter.out.persistence;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.empresa.ms_operaciones.application.port.out.ConexionBaseDatosPort;

@Repository
public class ConexionBaseDatosJdbcAdapter implements ConexionBaseDatosPort {
    private final JdbcTemplate jdbcTemplate;

    public ConexionBaseDatosJdbcAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean estaDisponible() {
        try {
            Integer resultado = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return resultado != null && resultado == 1;
        } catch (DataAccessException exception) {
            return false;
        }
    }
}
