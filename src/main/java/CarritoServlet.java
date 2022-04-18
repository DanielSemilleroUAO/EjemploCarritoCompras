
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author adseglocdom
 */
@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String articuloNuevo = req.getParameter("articulo");

        HttpSession sesion = req.getSession();

        List<String> articulos = (List<String>) sesion.getAttribute("articulos");
        
        if (articulos == null) {
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);
        }
        
        if (articuloNuevo != null && !articuloNuevo.trim().equals("")) {
            articulos.add(articuloNuevo);
        }
        
        try (PrintWriter out = resp.getWriter()) {
            out.print("<h1>Lista de articulos</h1>");
            for (String articulo : articulos) {
                out.print(articulo);
                out.print("<br />");
            }
            
            out.print("<br />");
            out.print("<a href='/EjemploCarritoCompras'>Regresar al inicio</a>");
        }

    }

}
