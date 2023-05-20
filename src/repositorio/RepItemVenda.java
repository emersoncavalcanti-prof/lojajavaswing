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
import model.ItemVenda;
import model.Produto;

public class RepItemVenda {
    
    Connection con;

    public boolean inserir(ItemVenda item){
        
        
        con = ConexaoMySql.getConexao(); 
        
        String sql = "insert into itemvenda (venda_id,"
                 + "valoruni,qtd,valortotal,produtos_id) values "
                 + "(?,?,?,?,?)";
         try{
             con.setAutoCommit(false);
             PreparedStatement stmt = con.prepareStatement(sql);
             
             stmt.setInt(1, item.getVenda_id());
             stmt.setDouble(2, item.getValoruni());
             stmt.setDouble(3, item.getQtd());
             stmt.setDouble(4, item.getValortotal());
             stmt.setInt(5, item.getProduto_id());
             
             stmt.execute();
             con.commit();
             
             ConexaoMySql.fecharConexao();
             
            return true;
         }catch(Exception ex){
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
    
  public List<ItemVenda> retornar(String idVenda){
      
      con = ConexaoMySql.getConexao();
      List<ItemVenda> itens = new ArrayList<>();
      
      String sql = "select i.*,p.descricao from itemvenda i, produtos p where i.produtos_id = p.id and i.venda_id  = "+idVenda+" order by id desc";
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              ItemVenda item = new ItemVenda();
              
              item.setId(rs.getInt("id"));
              item.setDescricao(rs.getString("descricao"));
              item.setQtd(rs.getDouble("qtd"));
              item.setValoruni(rs.getDouble("valoruni"));
              item.setValortotal(rs.getDouble("valortotal"));

              
              itens.add(item);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return itens;
  }  
  
      public boolean cancelar(int id) {

        con = ConexaoMySql.getConexao();
        String sql = "update venda set status = ? where id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, "C");
            stmt.setInt(2, id);

             
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
  
  public List<Produto> pesquisar(String valor, String tipoPesquisa){
      
      con = ConexaoMySql.getConexao();
      List<Produto> produtos = new ArrayList<>();
      
      String sql = ""; 
      
       if(tipoPesquisa.equals("descricao")){
       sql = "select * from produtos where descricao like '%"+valor+"%'";
      }else if(tipoPesquisa.equals("barras")){
       sql = "select * from produtos where codbarra = '"+valor+"'";
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
              produto.setPeso(rs.getDouble("peso"));
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
      String sql = "delete from produtos where id = ?";
      
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
  
   public int retornarTotal(){
      
      con = ConexaoMySql.getConexao();
      int ret = 0;
      
      String sql = "select count(*) as total from produtos";
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          
          while(rs.next()){
              ret = rs.getInt("total");             
          }            
      }catch(SQLException ex){
          return ret;
      }
      
      ConexaoMySql.fecharConexao();
      
      return ret;
  }  
    
    
}
