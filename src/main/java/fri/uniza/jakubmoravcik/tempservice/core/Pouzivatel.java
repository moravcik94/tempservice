package fri.uniza.jakubmoravcik.tempservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jakubmoravcik
 */
@Entity
@Table(name = "pouzivatelia")
@NamedQueries(
        {
            @NamedQuery(
                    name = "fri.uniza.jakubmoravcik.tempservice.core.Pouzivatel.findAll",
                    query = "SELECT p FROM Pouzivatel p"
            ),
            @NamedQuery(
                    name = "fri.uniza.jakubmoravcik.tempservice.core.Pouzivatel.findByUsername",
                    query = "from Pouzivatel where meno = :meno "
            )
        }
)
public class Pouzivatel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "meno", nullable = false)
    private String meno;

    @Column(name = "heslo", nullable = false)
    private String heslo;

    @Column(name = "email", nullable = false)
    private String email;

    public Pouzivatel() {
    }

    public Pouzivatel(String meno, String heslo, String email) {
        this.meno = meno;
        this.heslo = heslo;
        this.email = email;
    }

    public Pouzivatel(String meno, String heslo) {
        this.meno = meno;
        this.heslo = heslo;
        email = "daco@daco";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    
    public void setId(long id) {
        this.id = id;
    }
    
    @JsonProperty
    public String getMeno() {
        return meno;
    }
    
    @JsonProperty
    public String getHeslo() {
        return heslo;
    }
    
    @JsonProperty
    public String getEmail() {
        return email;
    }

}
