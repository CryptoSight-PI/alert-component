package sight.crypto.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import sight.crypto.entity.Alerta;

public class AlertaDAO {
    private JdbcTemplate template;

    public Boolean insertAlerta(Alerta alerta) {
//        if (alerta.getDataHoraResolucao() == null) {
//            alerta.setDataHoraResolucao(new Date(null));
//        }
        String script = "INSERT INTO alerta " +
                "(descricao, severidade, valor, dataHora, status, dataHoraResolucao) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            template.update(
                    script,
                    alerta.getDescricao(),
                    alerta.getSeveridade(),
                    alerta.getValor(),
                    alerta.getDataHora(),
                    alerta.getStatus(),
                    alerta.getDataHoraResolucao()
            );
            return true;
        } catch (InvalidResultSetAccessException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
