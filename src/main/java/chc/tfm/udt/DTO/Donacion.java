package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Donacion {

    private Long id;
    private String descripcion;
    private String observacion;
    private Date createAt;
    private Jugador jugador;
    private List<ItemDonacion> items;

    public Donacion(){
        this.items = new ArrayList<>();
    }

    public Donacion(DonacionEntity d) {
    }

    public void addItemDonacion(ItemDonacion linea) {
        this.items.add(linea);
    }

    public void addItemDonacion(ItemDonacionEntity linea) {
    }
}
