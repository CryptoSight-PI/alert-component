package sight.crypto.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Alerta {
    private Long id;
    private Long id_mc;
    private String descricao;
    private Integer severidade;
    private Double valor;
    private Date dataHora;
    private String status;
    private Date dataHoraResolucao;

    public Alerta() {
    }

    public Alerta(Long id, Long id_mc, String descricao, Integer severidade, Double valor, Date dataHora, String status, Date dataHoraResolucao) {
        this.id = id;
        this.id_mc = id_mc;
        this.descricao = descricao;
        this.severidade = severidade;
        this.valor = valor;
        this.dataHora = dataHora;
        this.status = status;
        this.dataHoraResolucao = dataHoraResolucao;
    }

    public String getDataHoraResolucao() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(dataHoraResolucao);
    }

    public void setDataHoraResolucao(Date dataHoraResolucao) {
        this.dataHoraResolucao = dataHoraResolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    @Override
    public String toString() {
        return "Alerta{" +
                "id=" + id +
                ", id_mc=" + id_mc +
                ", descricao='" + descricao + '\'' +
                ", severidade=" + severidade +
                ", valor=" + valor +
                ", dataHora=" + dataHora +
                ", status=" + status +
                ", dataHoraResolucao=" + dataHoraResolucao +
                '}';
    }

    @Override   
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alerta alerta = (Alerta) o;
        return Objects.equals(id, alerta.id) && Objects.equals(id_mc, alerta.id_mc) && Objects.equals(descricao, alerta.descricao) && Objects.equals(severidade, alerta.severidade) && Objects.equals(valor, alerta.valor) && Objects.equals(dataHora, alerta.dataHora) && Objects.equals(status, alerta.status) && Objects.equals(dataHoraResolucao, alerta.dataHoraResolucao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_mc, descricao, severidade, valor, dataHora, status, dataHoraResolucao);
    }
}