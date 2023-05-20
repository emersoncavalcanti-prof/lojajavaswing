
package utils;




import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    
    public Date formatarData(String valor){
         Date data = null;
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
         data = df.parse(valor);
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return data;
    }
    
    public String formatarMoeda(double valor){
        DecimalFormat df = new DecimalFormat("0.##");
        
        return df.format(valor);
    }
    
}
