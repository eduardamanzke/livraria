
package controle;

import dao.AutorDAO;
import dao.EditoraDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Autor;
import modelo.Editora;

@WebServlet(name = "AutorWS", urlPatterns = {"/AutorWS"})
public class AutorWS extends HttpServlet {

 
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("txtAcao");
       RequestDispatcher destino;
       String pagina;
       AutorDAO dao = new AutorDAO();
       Autor obj; 
       List<Autor> autores;
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
               autores = dao.listar();
               request.setAttribute("lista", autores);
               pagina = "list.jsp";
               break;
               
           default:
               String filtro = request.getParameter("txtFiltro");
               if(filtro==null){
                   autores = dao.listar();
               }else{
                   try { 
                         autores = dao.listar(filtro);
               } catch (Exception ex) {
                         autores = dao.listar();
                         msg = "Problemas no filtro";
                         request.setAttribute("msg", msg);
               }
               }
               
               request.setAttribute("lista", autores);
               pagina = "list.jsp"; 
               break; 
               }
       
       destino = request.getRequestDispatcher(pagina);
       destino.forward(request, response);

    
    }
    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Autor obj;
        AutorDAO dao = new AutorDAO();
        Boolean deucerto;
        String msg;
        String pagina;
        RequestDispatcher destino; 
        List <Autor> autores; 
        String id = request.getParameter("txtId"); 
        String nome = request.getParameter("txtAutor"); 
      
      if(id != null){
          obj = dao.buscarPorChavePrimaria(Long.parseLong(id)); 
      }else{
          obj = new Autor(); 
      }
       obj.setNome(nome);
       
       if(id != null){
           deucerto = dao.alterar(obj);
           pagina = "list.jsp";
           autores = dao.listar();
           request.setAttribute("lista", autores);
           if(deucerto){
               msg = "Autor "+obj.getNome()+" editado com sucesso";
           }else{
               msg = "Problemas ao editar o autor";
           }
       }else{
           deucerto = dao.incluir(obj);
           pagina = "add.jsp";
            if(deucerto){
               msg = "Autor "+obj.getNome()+" adicionado com sucesso";
           }else{
               msg = "Problemas ao adicionar o autor";
           }
       }
       
       request.setAttribute("msg", msg);
       destino = request.getRequestDispatcher(pagina);
       destino.forward(request, response);
    }



}
