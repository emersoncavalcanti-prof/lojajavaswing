
package model;

public class ItemVenda {

    private int id;
    private int venda_id;
    private double valoruni;
    private double qtd;
    private double valortotal;
    private int produto_id;
    private String descricao;
    

    public ItemVenda() {
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the venda_id
     */
    public int getVenda_id() {
        return venda_id;
    }

    /**
     * @param venda_id the venda_id to set
     */
    public void setVenda_id(int venda_id) {
        this.venda_id = venda_id;
    }

    /**
     * @return the valoruni
     */
    public double getValoruni() {
        return valoruni;
    }

    /**
     * @param valoruni the valoruni to set
     */
    public void setValoruni(double valoruni) {
        this.valoruni = valoruni;
    }

    /**
     * @return the qtd
     */
    public double getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    /**
     * @return the valortotal
     */
    public double getValortotal() {
        return valortotal;
    }

    /**
     * @param valortotal the valortotal to set
     */
    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    /**
     * @return the produto_id
     */
    public int getProduto_id() {
        return produto_id;
    }

    /**
     * @param produto_id the produto_id to set
     */
    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
