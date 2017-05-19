package fri.uniza.jakubmoravcik.tempservice.db;

import fri.uniza.jakubmoravcik.tempservice.core.Pouzivatel;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author jakubmoravcik
 */
public class PouzivatelDAO extends AbstractDAO<Pouzivatel>{
    
    public PouzivatelDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public Pouzivatel create(Pouzivatel pouzivatel){
//        return persist(pouzivatel);
        super.currentSession().save(pouzivatel);
        return pouzivatel;
    }
    
    public Session getCs(){
        return super.currentSession();
    }
    
    public Optional<Pouzivatel> findById(Long id) {
        
        return Optional.ofNullable(get(id));
    }
    
    public List findByUsername(String username) {
        Query query = namedQuery("fri.uniza.jakubmoravcik.tempservice.core.Pouzivatel.findByUsername");
        query.setParameter("meno", username);
        return query.list();
    }
    
    
    
    public List<Pouzivatel> findAll() {
        return list(namedQuery("fri.uniza.jakubmoravcik.tempservice.core.Pouzivatel.findAll"));
    }
}
