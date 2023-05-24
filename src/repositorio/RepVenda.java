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
import model.Venda;

public class RepVenda {
    
    Connection con;

    public int inserir(){
        
        int id = 0;
        con = ConexaoMySql.getConexao(); 
        
        String sql = "insert into venda (total,"
                 + "clientes_id,fpag_id) values "
                 + "(?,?,?)";
         try{
             con.setAutoCommit(false);
             PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
             
             stmt.setDouble(1, 0);
             stmt.setInt(2, 1);
             stmt.setInt(3, 1);
             
             stmt.executeUpdate();
             con.commit();
             
             
             ResultSet rs = stmt.getGeneratedKeys();
            
             if(rs.next()){
                 id = rs.getInt(1);
             }
            
             ConexaoMySql.fecharConexao();
            
         }catch(Exception ex){
             try{
                 con.rollback();
                 System.err.println(ex.getMessage());
                 return id;
             }catch(SQLException exSql){
                 System.err.println(exSql.getMessage());
             }
         }
         
       return id;
    }
    
     public boolean finalizar(Venda venda) {

        con = ConexaoMySql.getConexao();
        String sql = "update venda set total = ?, "
                + "fpag_id = ?,status = ? where id = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setDouble(1, venda.getTotal());
            stmt.setInt(2, venda.getFpag_id());
            stmt.setString(3, "F");
            stmt.setInt(4, venda.getId());
             
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
    
  public List<Venda> retornar(){
      
      con = ConexaoMySql.getConexao();
      List<Venda> vendas = new ArrayList<>();
      
      String sql = "select v.*,f.descricao,c.nome from venda v,fpag f, clientes c where  v.fpag_id = f.id and v.clientes_id = c.id and v.status <> 'I' order by v.id desc;";
      
      try{
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
              
              Venda venda = new Venda();
              
              venda.setId(rs.getInt("id"));
              venda.setData(rs.getTimestamp("data").toLocalDateTime());
              venda.setTotal(rs.getDouble("total"));
              venda.setCliente(rs.getString("nome"));
              venda.setFpag(rs.getString("descricao"));
              venda.setCliente_id(rs.getInt("clientes_id"));
              venda.setFpag_id(rs.getInt("fpag_id"));
              venda.setStatus(rs.getString("status"));
              
              vendas.add(venda);
          }            
      }catch(SQLException ex){
          return null;
      }
      
      ConexaoMySql.fecharConexao();
      
      return vendas;
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
