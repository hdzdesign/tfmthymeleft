package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.EquipoEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Jugador {
    private Long id;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String mail;
    private Date nacimiento;
    private String edad;
    private String nacionalidad;
    private Date inscripcion;
    private String dorsal;
    private String foto;
  //  private EquipoEntity equipo;
    private List<DonacionEntity> donaciones;

    public Jugador() {

    }
}
