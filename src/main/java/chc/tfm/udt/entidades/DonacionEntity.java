package chc.tfm.udt.entidades;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.Jugador;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que va a Persistir en base de datos los datos básicos de la adquisición de 1 equipación de futbol que el equipo
 * dona al jugador con su incorporación al equipo , automaticamente cuando un jugador entra en el equipo se le asigna 1 equipación
 * por tanto estará implementado en el registro del jugador.
 */
@Data
@Entity
@Table(name = "donaciones")
public class DonacionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String observacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;
    /**
     * Muchas Donaciones un solo jugador.
     * Existe una relación bidireccional.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Solo se realiza la consulta cuando se invoca al metodo
    private JugadorEntity jugadorEntity;
    /**
     * Una donación contiene muchos itemsDonaciónEntity.
     * JoinColum: Indicamos a la base de datos cual es la llave foranea de la relación ,
     * puesto que es una relación unidireccional.
     * Hay que crear el campo donacion_id en la tabla  en base datos no en el Entity.
     */
    @JoinColumn(name = "donacion_id")
    @OneToMany( fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<ItemDonacionEntity> items;

    //Metodo que usaremos para persistir la fecha justn en el momento de crear la claes
    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public Double getTotal() {
        Double total = 0.0;
        int size = items.size();
        for (int i = 0; i < size; i++) {
            total += items.get(i).calcularValor();
        }
        return total;
    }

    public DonacionEntity() {
    }
//    public String toString(){
//        return new Gson().toJson(this);
//    }
}
