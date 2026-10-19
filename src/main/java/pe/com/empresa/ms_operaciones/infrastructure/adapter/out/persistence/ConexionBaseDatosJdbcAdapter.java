package pe.com.empresa.ms_operaciones.infrastructure.adapter.out.persistence;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.com.empresa.ms_operaciones.application.port.out.ConexionBaseDatosPort;

@Repository
public class ConexionBaseDatosJdbcAdapter implements ConexionBaseDatosPort {
    private static final Logger log = LoggerFactory.getLogger(ConexionBaseDatosJdbcAdapter.class);

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
            log.error("No se pudo validar la conexión con la base de datos", exception);
            return false;
        }
    }
}
