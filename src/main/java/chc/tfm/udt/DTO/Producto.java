package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.ProductoEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Producto {

    private Long id;
    private String nombre;
    private Double precio;
    private Date createAt;

    public Producto() {
    }

    public Producto(ProductoEntity productoEntity) {
    }
}
