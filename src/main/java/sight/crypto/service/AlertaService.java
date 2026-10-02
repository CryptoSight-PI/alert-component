package sight.crypto.service;

import sight.crypto.config.S3Provider;
import sight.crypto.dao.AlertaDAO;
import sight.crypto.entity.Alerta;

import java.io.FileWriter;

public class AlertaService {
    private Alerta alerta;
    private AlertaDAO alertaDAO;
    private S3Provider s3Provider;

    public void leituraCSV() {
        alertaDAO.getS3Objects();
//        FileWriter fileWriter = new FileWriter();
        // 1. o objeto s3 vai ser um csv
        // 2. filewriter vai ler o csv
        // 3. o metood vai definir o que é alerta
        // 4. outro metodo vai mandar pro jira
    }
}
