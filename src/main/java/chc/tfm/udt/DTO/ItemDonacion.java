package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.ItemDonacionEntity;
import com.google.gson.Gson;
import lombok.Data;

@Data
public class ItemDonacion {

    private Long id;
    private Integer cantidad;
    private Producto producto;

    public ItemDonacion(){}

    public String toString(){
        return new Gson().toJson(this);
    }
}
