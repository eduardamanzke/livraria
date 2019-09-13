
package controle;

import dao.GeneroDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Genero;



@WebServlet(name = "GeneroWS", urlPatterns = {"/admin/genero/GeneroWS"})
public class GeneroWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String acao = request.getParameter("txtAcao");
       RequestDispatcher destino;
       String pagina;
       GeneroDAO dao = new GeneroDAO();
       Genero obj; 
       List<Genero> generos;
       Boolean deucerto;
       String msg;
       switch(String.valueOf(acao)){
           case "add":
               pagina = "add.jsp";
               break;
           case "edit":
               obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtId"))); 
               request.setAttribute("obj", obj);
               pagina = "edit.jsp";
               break;
           case "del":
               obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtId"))); 
               deucerto = dao.excluir(obj);
               if(deucerto){
                  msg = "deletado com sucesso"; 
               }else{
                   msg = "problemas ao deletar";
               }
               request.setAttribute("msg", msg);
               generos = dao.listar();
               request.setAttribute("lista", generos);
               pagina = "list.jsp";
               break;
               
           default:
               String filtro = request.getParameter("txtFiltro");
               if(filtro==null){
                   generos = dao.listar();
               }else{
                   try { 
                         generos = dao.listar(filtro);
               } catch (Exception ex) {
                         generos = dao.listar();
                         msg = "Problemas no filtro";
                         request.setAttribute("msg", msg);
               }
                   
               request.setAttribute("lista", generos);
               pagina = "list.jsp"; 
               break; 
               }
       destino = request.getRequestDispatcher(pagina);
       destino.forward(request, response);

    }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Genero obj;
        GeneroDAO dao = new GeneroDAO();
        Boolean deucerto;
        String msg;
        String pagina;
        RequestDispatcher destino; 
        List <Genero> generos; 
        String id = request.getParameter("txtId"); 
        String nome = request.getParameter("txtGenero"); 
      
      if(id != null){
          obj = dao.buscarPorChavePrimaria(Long.parseLong(id)); 
      }else{
          obj = new Genero(); 
      }
       obj.setNome(nome);
       
       if(id != null){
           deucerto = dao.alterar(obj);
           pagina = "list.jsp";
           generos = dao.listar();
           request.setAttribute("lista", generos);
           if(deucerto){
               msg = "Genero "+obj.getNome()+" editado com sucesso";
           }else{
               msg = "Problemas ao editado o gênero";
           }
       }else{
           deucerto = dao.incluir(obj);
           pagina = "add.jsp";
            if(deucerto){
               msg = "Genero "+obj.getNome()+" adicionado com sucesso";
           }else{
               msg = "Problemas ao adicionar o gênero";
           }
       }
       
       request.setAttribute("msg", msg);
       destino = request.getRequestDispatcher(pagina);
       destino.forward(request, response);
    }
    
   

}
