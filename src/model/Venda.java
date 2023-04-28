package model;

import java.util.Date;

public class Venda {

    private int id;
    private double total;
    private Date data;
    private int cliente_id;
    private int fpag_id;
    private String fpag;
    private String cliente;    

    public Venda() {
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
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the cliente_id
     */
    public int getCliente_id() {
        return cliente_id;
    }

    /**
     * @param cliente_id the cliente_id to set
     */
    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    /**
     * @return the fpag_id
     */
    public int getFpag_id() {
        return fpag_id;
    }

    /**
     * @param fpag_id the fpag_id to set
     */
    public void setFpag_id(int fpag_id) {
        this.fpag_id = fpag_id;
    }

    /**
     * @return the fpag
     */
    public String getFpag() {
        return fpag;
    }

    /**
     * @param fpag the fpag to set
     */
    public void setFpag(String fpag) {
        this.fpag = fpag;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    
    
}
