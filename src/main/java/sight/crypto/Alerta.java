package sight.crypto;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Alerta {

    void alertar(Double leitura, String nome, Double limite, String unidade){
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

    void validar(ArrayList<Integer> componentes, ArrayList<String> unidades, ArrayList<Double> limitesMax, ArrayList<Double> limitesMin, ArrayList<Double> leituras, ArrayList<String> nomesComp){
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
}