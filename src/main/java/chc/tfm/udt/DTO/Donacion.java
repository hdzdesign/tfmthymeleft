package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Donacion {

    private Long id;
    private String descripcion;
    private String observacion;
    private Date createAt;
    private JugadorEntity jugadorEntity;
    private List<ItemDonacionEntity> items;
}
