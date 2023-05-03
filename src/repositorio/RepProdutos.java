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
import model.Produto;

public class RepProdutos {
    
    Connection con;

    public boolean inserir(Produto produto){
        
        con = ConexaoMySql.getConexao(); 
        
        String sql = "insert into tbproduto (descricao,"
                 + "qtd,valor,codbarra) values "
                 + "(?,?,?,?,?)";
         try{
             con.setAutoCommit(false);
             PreparedStatement stmt = con.prepareStatement(sql);
             
             stmt.setString(1, produto.getDescricao());
             stmt.setDouble(3, produto.getQtd());
             stmt.setDouble(4, produto.getValor());
             stmt.setString(5, produto.getCodbarra());
             
             stmt.execute();
             con.commit();
             ConexaoMySql.fecharConexao();
             
            return true;
         }catch(SQLException ex){
             try{
                 con.rollback();
                 System.err.println(ex.getMessage());
                 return false;
             }catch(SQLException exSql){
                 System.err.println(exSql.getMessage());
             }
         }
         
       return true;
    }
    
  public List<Produto> retornar(){
      
      con = ConexaoMySql.getConexao();
      List<Produto> produtos = new ArrayList<>();
      
      String sql = "select * from tbproduto order by id desc";
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              Produto produto = new Produto();
              
              produto.setId(rs.getInt("id"));
              produto.setDescricao(rs.getString("descricao"));
              produto.setQtd(rs.getDouble("qtd"));
              produto.setValor(rs.getDouble("valor"));
              produto.setCodbarra(rs.getString("codbarra"));
              
              produtos.add(produto);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return produtos;
  }  
  
      public boolean atualizar(Produto produto) {

        con = ConexaoMySql.getConexao();
        String sql = "update tbproduto set descricao = ?, "
                + "valor = ?,qtd = ?, codbarra = ? where id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getValor());
            stmt.setDouble(3, produto.getQtd());
            stmt.setString(4, produto.getCodbarra());
            stmt.setInt(5, produto.getId());
             
            stmt.execute();

            con.commit();
            ConexaoMySql.fecharConexao();

            return true;

        } catch (SQLException ex) {
            try {
                con.rollback();
                System.err.println(ex);
                return false;
            } catch (SQLException ex1) {
                System.err.println(ex1);
            }

            return false;
        }

    }  
  
  public List<Produto> pesquisa(String valor, String tipoPesquisa){
      
      con = ConexaoMySql.getConexao();
      List<Produto> produtos = new ArrayList<>();
      
      String sql = "";
      
      if(tipoPesquisa.equals("comeca")){
       sql = "select * from tbproduto where descricao like '"+valor+"%'";
      }else if(tipoPesquisa.equals("contem")){
       sql = "select * from tbproduto where descricao like '%"+valor+"%'";
      }else if(tipoPesquisa.equals("barras")){
       sql = "select * from tbproduto where codbarra = '"+valor+"'";
      }else if(tipoPesquisa.equals("codigo")){
       sql = "select * from tbproduto where id = '"+valor+"'";
      }
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              Produto produto = new Produto();
              
              produto.setId(rs.getInt("id"));
              produto.setDescricao(rs.getString("descricao"));
              produto.setQtd(rs.getDouble("qtd"));
              produto.setValor(rs.getDouble("valor"));
              produto.setCodbarra(rs.getString("codbarra"));
              
              produtos.add(produto);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return produtos;
  }  
    
  
  public boolean excluir(int id){
      
      con = ConexaoMySql.getConexao();
      String sql = "delete from tbproduto where id = ?";
      
      try{
          
          con.setAutoCommit(false);
          PreparedStatement stmt = con.prepareStatement(sql);
          
          stmt.setInt(1, id);
          
          stmt.execute();
          con.commit();
          ConexaoMySql.fecharConexao();
      
          return true;   
      }catch(SQLException ex){
          
          return false;
      }
        
  }
    
    
}
