<%@include file="../cabecalho.jsp" %>

<div class="card ">
    <div class="card-header">
        <h4 class="card-title">Genero</h4>
    </div>
    <div class="card-body">
        <a class="btn btn-primary btn-round text-center" href="add.jsp">
            <i class="tim-icons icon-simple-add"></i> Adiciona
        </a>
        <div class="table-responsive">
            <table class="table tablesorter " id="">
                <thead class=" text-primary">
            <tr>
                <th>
                    ID
                </th>
                <th>
                    Genero
                </th>
                <th>
                    Edita
                </th>
                <th>
                    Deleta
                </th>
            </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="obj">
                        <tr>
                            <td>${obj.id}</td>
                            <td>${obj.nome}</td>
                            <td>
                                <a class="btn btn-info btn-fab btn-icon btn-round" href="">
                                    <i class="tim-icons icon-pencil"></i>
                                </a>
                            </td>
                            <td>
                                <a class="btn btn-primary btn-fab btn-icon btn-round" href="GeneroWS?txtAcao=del&txtId=${obj.id}">
                                    <i class="tim-icons icon-trash-simple"></i>
                                </a>
                            </td>
                        </tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="card-footer">
       
            <div class="alert alert-primary alert-dismissible fade show" role="alert">
                
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
        
    </div>
</div>

<%@include file="../rodape.jsp" %>