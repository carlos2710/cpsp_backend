package pe.com.empresa.ms_operaciones.infrastructure.adapter.out.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pe.com.empresa.ms_operaciones.application.port.out.MenuPerfilPort;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response.MenuPerfilResponse;

import java.util.List;

@Repository
public class MenuPerfilJdbcAdapter implements MenuPerfilPort {
    private final JdbcTemplate jdbcTemplate;

    public MenuPerfilJdbcAdapter (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MenuPerfilResponse> listarPorPerfil(Integer codPerfil) {
        String sql = """
            EXEC dbo.sp_listar_menu_por_perfil
                @CodPerfil = ?
            """;

        return jdbcTemplate.query(
                sql,
                rowMapper,
                codPerfil
        );
    }

    private final RowMapper<MenuPerfilResponse> rowMapper =
            (rs, rowNum) -> new MenuPerfilResponse(
                    rs.getString("perfil"),
                    rs.getInt("codigo_modulo"),
                    rs.getString("modulo"),
                    rs.getInt("codigo_submenu"),
                    rs.getString("submenu"),
                    rs.getString("ruta"),
                    rs.getInt("orden")
            );
}
