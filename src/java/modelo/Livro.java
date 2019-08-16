
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;


@Entity
@NamedQueries({
    @NamedQuery(name ="Livro.findAll", query="SELECT d FROM Livro d"),
    @NamedQuery(name = "Livro.findFilter", query="SELECT d FROM Livro d WHERE d.nome LIKE :filtro")
})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date datapub; 
    private Long paginas;
    private String sinopse;
    private Long isbn;
    private String idioma;
    private String img; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDatapub() {
        return datapub;
    }

    public void setDatapub(Date datapub) {
        this.datapub = datapub;
    }

    public Long getPaginas() {
        return paginas;
    }

    public void setPaginas(Long paginas) {
        this.paginas = paginas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    
}
