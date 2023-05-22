
package utils;




import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Utils {
    

    
    public String formatarDataHora(LocalDateTime dataHora){

            DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return dataHora.format(formatoBrasileiro);
    }
    
    public String formatarMoeda(double x) {
            return String.format("%.2f", x);
    }
    
    
    
}
