
package dao;

import modelo.Autor;

public class AutorDAO extends GenericDAO<Autor, Long> {
    public AutorDAO(){
        super(Autor.class);
    }
}
