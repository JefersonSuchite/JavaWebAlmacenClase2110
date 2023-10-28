import Clases.Producto;
import Clases.ProductoController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ProductoController productoController = new ProductoController();
            String action = request.getParameter("action");

            if ("guardar".equalsIgnoreCase(action)) {
                Producto producto = new Producto();
                producto.setNombre(request.getParameter("nombre"));
                producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                producto.setExistencia(Integer.parseInt(request.getParameter("existencia")));
                producto.setIdProducto(request.getParameter("id_producto")); // Cambia el tipo de id a String
                producto.setIdMarca(request.getParameter("id_marca")); // Asegúrate de obtener id_marca de algún lugar
                producto.setIdCategoria(request.getParameter("id_categoria")); // Asegúrate de obtener id_categoria de algún lugar
                productoController.guardarProducto(producto);
            } else if ("eliminar".equalsIgnoreCase(action)) {
                String idProducto = request.getParameter("id_producto"); // Cambia el tipo de id a String
                productoController.eliminarProducto(idProducto);
            }

            // Consulta y muestra los productos en formato HTML
            StringBuilder responseHtml = new StringBuilder();
            responseHtml.append("<html><body>");
            responseHtml.append("<h1>Lista de Productos</h1>");
            responseHtml.append("<ul>");

            for (Producto producto : productoController.getListaProductos()) {
                responseHtml.append("<li>").append(producto.getNombre()).append(" - Precio: ").append(producto.getPrecio()).append("</li>");
            }

            responseHtml.append("</ul>");
            responseHtml.append("</body></html>");
            out.println(responseHtml.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}
