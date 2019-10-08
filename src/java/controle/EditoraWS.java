
package controle;

import dao.EditoraDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Editora;




@WebServlet(name = "EditoraWS", urlPatterns = {"/EditoraWS"})
public class EditoraWS extends HttpServlet {

  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    String acao = request.getParameter("txtAcao");
       RequestDispatcher destino;
       String pagina;
       EditoraDAO dao = new EditoraDAO();
       Editora obj; 
       List<Editora> editoras;
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
               editoras = dao.listar();
               request.setAttribute("lista", editoras);
               pagina = "list.jsp";
               break;
               
           default:
               String filtro = request.getParameter("txtFiltro");
               if(filtro==null){
                   editoras = dao.listar();
               }else{
                   try { 
                         editoras = dao.listar(filtro);
               } catch (Exception ex) {
                         editoras = dao.listar();
                         msg = "Problemas no filtro";
                         request.setAttribute("msg", msg);
               }
               }
               
               request.setAttribute("lista", editoras);
               pagina = "list.jsp"; 
               break; 
               }
       
       destino = request.getRequestDispatcher(pagina);
       destino.forward(request, response);

    
    }


   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        Editora obj;
        EditoraDAO dao = new EditoraDAO();
        Boolean deucerto;
        String msg;
        String pagina;
        RequestDispatcher destino; 
        List <Editora> editoras; 
        String id = request.getParameter("txtId"); 
        String nome = request.getParameter("txtEditora"); 
      
      if(id != null){
          obj = dao.buscarPorChavePrimaria(Long.parseLong(id)); 
      }else{
          obj = new Editora(); 
      }
       obj.setNome(nome);
       
       if(id != null){
           deucerto = dao.alterar(obj);
           pagina = "list.jsp";
           editoras = dao.listar();
           request.setAttribute("lista", editoras);
           if(deucerto){
               msg = "Editora "+obj.getNome()+" editado com sucesso";
           }else{
               msg = "Problemas ao editar a editora";
           }
       }else{
           deucerto = dao.incluir(obj);
           pagina = "add.jsp";
            if(deucerto){
               msg = "Editora "+obj.getNome()+" adicionado com sucesso";
           }else{
               msg = "Problemas ao adicionar a editora";
           }
       }
       
       request.setAttribute("msg", msg);
       destino = request.getRequestDispatcher(pagina);
       destino.forward(request, response);
    }
    

  

}
