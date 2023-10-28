import Clases.ConexionBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AgregarProducto")
public class AgregarProductoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ConexionBaseDeDatos conexionBaseDeDatos = new ConexionBaseDeDatos();
        Connection connection = conexionBaseDeDatos.conectar();

        if (connection != null) {
            try {
                String idProducto = request.getParameter("id_producto");
                String nombre = request.getParameter("nombre");
                double precio = Double.parseDouble(request.getParameter("precio"));
                int existencia = Integer.parseInt(request.getParameter("existencia"));
                int idMarca = Integer.parseInt(request.getParameter("id_marca"));
                int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));

                String consulta = "INSERT INTO producto (id_producto, nombre_productocol, precio, existencia, id_marca, id_categoria, fecha_vencimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {
                    preparedStatement.setString(1, idProducto);
                    preparedStatement.setString(2, nombre);
                    preparedStatement.setDouble(3, precio);
                    preparedStatement.setInt(4, existencia);
                    preparedStatement.setInt(5, idMarca);
                    preparedStatement.setInt(6, idCategoria);
                    preparedStatement.setDate(7, null); // Aquí puedes establecer la fecha de vencimiento, o dejarla como NULL si no la utilizas

                    int filasAfectadas = preparedStatement.executeUpdate();

                    if (filasAfectadas > 0) {
                        response.sendRedirect("index.html"); // Redirige al formulario después de agregar el producto
                    } else {
                        response.getWriter().println("No se pudo agregar el producto.");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            response.getWriter().println("No se pudo conectar a la base de datos.");
        }
    }
}
