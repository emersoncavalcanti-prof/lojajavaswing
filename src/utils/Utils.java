
package utils;





import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Utils {
    

    
    public String formatarDataHora(LocalDateTime dataHora){

            DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return dataHora.format(formatoBrasileiro);
    }
    
    public LocalDateTime transformarStringData(String data){

    DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime dateTime = LocalDate.parse(data, parser).atStartOfDay();
    
    return dateTime;
       
    }
    
    public String formatarMoeda(double x) {
            return String.format("%.2f", x);
    }
    
    
    
}
