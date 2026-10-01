package sight.crypto.entity;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Alerta {
    private Long id;
    private Long id_mc;
    private String descricao;
    private Integer severidade;
    private Double valor;
    private Date dataHora;
    private Boolean status;
    private Date dataHoraResolucao;

    public Alerta() {
    }

    public Alerta(Long id, Long id_mc, String descricao, Integer severidade, Double valor, Date dataHora, Boolean status, Date dataHoraResolucao) {
        this.id = id;
        this.id_mc = id_mc;
        this.descricao = descricao;
        this.severidade = severidade;
        this.valor = valor;
        this.dataHora = dataHora;
        this.status = status;
        this.dataHoraResolucao = dataHoraResolucao;
    }

    public void alertar(Double leitura, String nome, Double limite, String unidade){
        LocalDateTime dataHora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dataHora.format(formatter);
        Double porcentagem  = ((leitura - limite) * 100.0) / limite;
        String indicador = "acima";
        if(porcentagem < 0){
            indicador = "abaixo";
            porcentagem *= -1;
        }
        String mensagem = """
                Seu %s está %.2f%% %s do limite de %.2f %s
                Leitura atual: %.2f %s
                Horário da Leitura: %s
                """.formatted(nome, porcentagem, indicador, limite, unidade, leitura, unidade, dataFormatada);

        System.out.println(mensagem);

        try (FileWriter escritor = new FileWriter("alertas.log", true)) {
            escritor.write(mensagem + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao registrar: " + e.getMessage());
        }
    }

    public void validar(ArrayList<Integer> componentes, ArrayList<String> unidades, ArrayList<Double> limitesMax, ArrayList<Double> limitesMin, ArrayList<Double> leituras, ArrayList<String> nomesComp){
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < componentes.size(); i++) {
            if(componentes.get(i).equals(1)){
                indices.add(i);
            }
        }
        for (int i = 0; i < indices.size(); i++) {
            Double limiteMin = limitesMin.get(indices.get(i));
            Double limiteMax = limitesMax.get(indices.get(i));
            Double leitura = leituras.get(indices.get(i));
            String nomeComp = nomesComp.get(indices.get(i));
            String unidade = unidades.get(indices.get(i));

            if (limiteMin == null) {
                if( leitura > limiteMax){
                    alertar(leitura, nomeComp, limiteMax, unidade);
                }
            } else if (limiteMax == null) {
                if(leitura < limiteMin){
                    alertar(leitura, nomeComp, limiteMin, unidade);
                }
            } else {
                if(leitura < limiteMin){
                    alertar(leitura, nomeComp, limiteMin, unidade);
                } else if (leitura > limiteMax) {
                    alertar(leitura, nomeComp, limiteMax, unidade);
                }
            }
        }
    }

    public void rodar() {
        Alerta alerta = new Alerta();
        ArrayList<Integer> componentes = new ArrayList<>(List.of(1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1));
        ArrayList<String> unidades = new ArrayList<>(List.of("%", "GHz", "%", "GiB", "GiB", "%", "Mbps", "Mbps",
                "°C", "RPM", "GiB", "%", "W"));
        ArrayList<Double> limitesMax = new ArrayList<>(Arrays.asList(70.0, 4.0, 90.0, 10.0, 8.0, 90.0, null, null, 65.0, 1000.0,
                null, 80.0, 450.0));
        ArrayList<Double> limitesMin = new ArrayList<>(Arrays.asList(null, 0.4, null, null, null, null, 100.0, 10.0, null, 300.0, 20.0, null, null));
        ArrayList<Double> leituras = new ArrayList<>(List.of(65.0, 3.5, 92.0, 0.0, 0.0, 97.0, 60.0, 0.0, 80.0, 0.0, 0.0, 70.0, 500.0));
        ArrayList<String> nomesComp = new ArrayList<>(List.of("cpu_perc", "cpu_freq", "ram_perc", "swap_total",
                "swap_used", "swap_percent", "upload_speed", "download_speed", "temperature", "fans_speed",
                "disk", "gpu_usage", "gpu_energy"));

        alerta.validar(componentes, unidades, limitesMax, limitesMin, leituras, nomesComp);
    }

    public String getHoraResolucao() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(dataHoraResolucao);
    }

    public void setDataHoraResolucao(Date dataHoraResolucao) {
        this.dataHoraResolucao = dataHoraResolucao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getId_mc() {
        return id_mc;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getSeveridade() {
        return severidade;
    }

    public Double getValor() {
        return valor;
    }

    public String getDataHora() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(dataHora);
    }
}