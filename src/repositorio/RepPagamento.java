package repositorio;

import com.mysql.jdbc.MySQLConnection;
import conn.ConexaoMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Fpag;
import model.Produto;
import model.Venda;

public class RepPagamento {
    
    Connection con;

   
    
  public List<Fpag> retornar(){
      
      con = ConexaoMySql.getConexao();
      List<Fpag> pagamentos = new ArrayList<>();
      
      String sql = "select * from fpag order by id desc";
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              Fpag fpag = new Fpag();
              
              fpag.setId(rs.getInt("id"));
              fpag.setDescricao(rs.getString("descricao"));
              
              pagamentos.add(fpag);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return pagamentos;
  }  
  
    
    
    
}
