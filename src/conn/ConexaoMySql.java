package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexaoMySql {

    public static Connection con = null;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String str_connection = "jdbc:mysql://localhost/loja";
    private static final String user = "root";
    private static final String senha = "";

    public static Connection getConexao() {
        try {
            Class.forName(driver);
            try {
                con = DriverManager.getConnection(str_connection, user, senha);
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoMySql.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha na Conexão! MSG: "+ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(ConexaoMySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro no Driver SQL ! MSG: "+ex.getMessage());
        }

        return con;
    }
    
    public static void fecharConexao(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoMySql.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão! MSG: "+ex.getMessage());
        }
    }
}
