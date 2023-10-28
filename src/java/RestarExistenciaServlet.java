import Clases.ConexionBaseDeDatos;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/RestarExistencia")
public class RestarExistenciaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ConexionBaseDeDatos conexionBaseDeDatos = new ConexionBaseDeDatos();
        Connection connection = conexionBaseDeDatos.conectar();

        if (connection != null) {
            try {
                String idProducto = request.getParameter("id_producto_restar");
                int cantidadRestar = Integer.parseInt(request.getParameter("cantidad_restar"));

                // Verificar que el producto existe y tiene suficiente existencia
                String consultaExistencia = "SELECT existencia FROM producto WHERE id_producto = ?";
                try (PreparedStatement preparedStatementExistencia = connection.prepareStatement(consultaExistencia)) {
                    preparedStatementExistencia.setString(1, idProducto);
                    preparedStatementExistencia.executeQuery();

                    if (preparedStatementExistencia.getResultSet().next()) {
                        int existenciaActual = preparedStatementExistencia.getResultSet().getInt("existencia");
                        if (existenciaActual >= cantidadRestar) {
                            // Restar la cantidad de existencia
                            String consultaRestarExistencia = "UPDATE producto SET existencia = existencia - ? WHERE id_producto = ?";
                            try (PreparedStatement preparedStatementRestarExistencia = connection.prepareStatement(consultaRestarExistencia)) {
                                preparedStatementRestarExistencia.setInt(1, cantidadRestar);
                                preparedStatementRestarExistencia.setString(2, idProducto);
                                preparedStatementRestarExistencia.executeUpdate();
                                response.getWriter().println("Existencia restada con éxito.");
                            }
                        } else {
                            response.getWriter().println("No hay suficiente existencia para restar.");
                        }
                    } else {
                        response.getWriter().println("El producto con ID " + idProducto + " no existe.");
                    }
                }
            } catch (SQLException ex) {
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
