package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.ProductoEntity;
import lombok.Data;

@Data
public class ItemDonacion {

    private Long id;
    private Integer cantidad;
    private ProductoEntity productoEntity;

}
