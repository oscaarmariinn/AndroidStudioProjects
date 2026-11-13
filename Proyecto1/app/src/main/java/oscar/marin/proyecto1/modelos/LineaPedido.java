package oscar.marin.proyecto1.modelos;

import java.io.Serializable;

public class LineaPedido implements Serializable {
    private String producto;
    private String tipo;
    private int cantidad;
    private double precioUnitario;

    public LineaPedido(String producto, String tipo, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getSubtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "producto='" + producto + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}