package Clases;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ProductoDAO {
    private ConexionBaseDeDatos conexionBaseDeDatos;

    public ProductoDAO() {
        conexionBaseDeDatos = new ConexionBaseDeDatos();
    }

    public void consultarProductos() {
        Connection connection = conexionBaseDeDatos.conectar();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String consulta = "SELECT * FROM producto";
                ResultSet resultSet = statement.executeQuery(consulta);

                // Realiza operaciones con los resultados aquí
                while (resultSet.next()) {
                    // Procesa los datos de cada fila
                    int idProducto = resultSet.getInt("id_producto");
                    String nombreProducto = resultSet.getString("nombre_productocol");
                    double precio = resultSet.getDouble("precio");
                    int existencia = resultSet.getInt("existencia");
                    int idMarca = resultSet.getInt("id_marca");
                    int idCategoria = resultSet.getInt("id_categoria");

                    // Realiza lo que necesites con estos datos
                    System.out.println("ID: " + idProducto + ", Nombre: " + nombreProducto);
                }

                resultSet.close();
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }
}
