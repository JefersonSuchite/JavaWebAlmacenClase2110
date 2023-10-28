package Clases;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int existencia;
    private String idProducto; // Cambiado a String según tu comentario
    private String idMarca; // Agregada variable idMarca
    private String idCategoria; // Agregada variable idCategoria

    public Producto() {
    }

    public Producto(String nombre, double precio, int existencia, String idProducto, String idMarca, String idCategoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.existencia = existencia;
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.idCategoria = idCategoria;
    }

    // Getters y setters para los atributos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}
