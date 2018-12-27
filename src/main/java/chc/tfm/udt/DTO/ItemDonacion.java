package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.ItemDonacionEntity;
import lombok.Data;

@Data
public class ItemDonacion {

    private Long id;
    private Integer cantidad;
    private Producto producto;

    public ItemDonacion(){}

    public ItemDonacion(ItemDonacionEntity o) {
    }
}
