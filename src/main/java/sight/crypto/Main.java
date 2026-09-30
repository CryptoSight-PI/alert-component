package sight.crypto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

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
}