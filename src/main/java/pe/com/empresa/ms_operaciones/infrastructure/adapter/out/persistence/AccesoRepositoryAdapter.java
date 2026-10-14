package pe.com.empresa.ms_operaciones.infrastructure.adapter.out.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.empresa.ms_operaciones.application.port.out.AccesoRepositoryPort;
import pe.com.empresa.ms_operaciones.domain.model.UsuarioAcceso;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.AccesoResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AccesoRepositoryAdapter  implements AccesoRepositoryPort {
    private final JdbcTemplate jdbcTemplate;
    public AccesoRepositoryAdapter(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UsuarioAcceso> validarCredenciales(String userName) {
        String sql = """
                EXEC dbo.usp_ObtenerUsuarioLogin
                @Usuario = ?
                """;

        List<UsuarioAcceso> usuarios = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> mapearUsuario(rs),
                userName
        );

        return usuarios.stream().findFirst();
    }

    @Override
    public List<AccesoResponse> obtenerLocales(Integer idUsuario) {
        String sql = """
                EXEC dbo.sp_listar_locales_usuario
                @UsuarioCodigo=?
                """;

        List<AccesoResponse> list = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> mapearLocal(rs),
                idUsuario
        );

        return list;
    }

    private UsuarioAcceso mapearUsuario(ResultSet rs)
            throws SQLException {

        return new UsuarioAcceso(
                rs.getInt("usu_codigo"),
                rs.getString("usu_usuario"),
                rs.getString("usu_clave"),
                rs.getInt("usu_estado"),
                rs.getInt("usu_marca")
        );
    }

    private AccesoResponse mapearLocal(ResultSet rs)
            throws SQLException {

        return new AccesoResponse(
                rs.getInt("loc_codigo"),
                (Integer) rs.getObject("loc_codempresa"),
                rs.getInt("loc_orden"),
                rs.getString("loc_descripcion")
        );
    }
}
