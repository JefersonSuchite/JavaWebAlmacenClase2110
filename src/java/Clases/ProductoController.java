package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoController {
    private ConexionBaseDeDatos conectorBD;
    private Connection conexion;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public ProductoController() {
        conectorBD = new ConexionBaseDeDatos();
        conexion = conectorBD.conectar();
    }

    public String guardarProducto(Producto producto) {
        String sql = "INSERT INTO producto (id_producto, nombre_productocol, precio, existencia, id_marca, id_categoria) ";
        sql += "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setString(1, producto.getIdProducto()); // Aquí proporciona el id_producto
            statement.setString(2, producto.getNombre());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getExistencia());
            statement.setString(5, producto.getIdMarca());
            statement.setString(6, producto.getIdCategoria());
            int resultado = statement.executeUpdate();
            if (resultado > 0) {
                return String.valueOf(resultado);
            } else {
                return String.valueOf(resultado);
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<Producto> getListaProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try {
            statement = conexion.prepareStatement(sql);
            result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    Producto producto = new Producto();
                    producto.setIdProducto(result.getString("id_producto"));
                    producto.setNombre(result.getString("nombre_productocol"));
                    producto.setPrecio(result.getDouble("precio"));
                    producto.setExistencia(result.getInt("existencia"));
                    producto.setIdMarca(result.getString("id_marca"));
                    producto.setIdCategoria(result.getString("id_categoria"));
                    productos.add(producto);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productos;
    }

    public String eliminarProducto(String idProducto) {
        String sql = "DELETE FROM producto WHERE id_producto=?";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setString(1, idProducto);
            int resultado = statement.executeUpdate();
            if (resultado > 0) {
                return String.valueOf(resultado);
            } else {
                return String.valueOf(resultado);
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
