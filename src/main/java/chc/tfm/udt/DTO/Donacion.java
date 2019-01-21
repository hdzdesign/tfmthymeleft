package chc.tfm.udt.DTO;


import chc.tfm.udt.entidades.ItemDonacionEntity;
import lombok.Data;
import com.google.gson.Gson;

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

    public void addItemDonacion(ItemDonacion linea) {
        this.items.add(linea);
    }

    public void addItemDonacion(ItemDonacionEntity linea) {
    }

    public String toString(){
        return new Gson().toJson(this);
    }
}
