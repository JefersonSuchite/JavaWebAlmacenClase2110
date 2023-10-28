import Clases.ConexionBaseDeDatos;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet("/MostrarProductos")
public class MostrarProductosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ConexionBaseDeDatos conexionBaseDeDatos = new ConexionBaseDeDatos();
        Connection connection = conexionBaseDeDatos.conectar();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String consulta = "SELECT * FROM producto";
                ResultSet resultSet = statement.executeQuery(consulta);

                // Construir una tabla HTML para mostrar los datos
                out.println("<html><body><table border='1'><tr><th>ID</th><th>Nombre</th><th>Precio</th><th>Existencia</th></tr>");
                while (resultSet.next()) {
                    int idProducto = resultSet.getInt("id_producto");
                    String nombreProducto = resultSet.getString("nombre_productocol");
                    double precio = resultSet.getDouble("precio");
                    int existencia = resultSet.getInt("existencia");
                    out.println("<tr><td>" + idProducto + "</td><td>" + nombreProducto + "</td><td>" + precio + "</td><td>" + existencia + "</td></tr>");
                }
                out.println("</table></body></html>");

                resultSet.close();
                statement.close();
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
            out.println("No se pudo conectar a la base de datos.");
        }
    }
}
