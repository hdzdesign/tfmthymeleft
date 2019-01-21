package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.ProductoEntity;
import com.google.gson.Gson;
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
    public String toString(){
        return new Gson().toJson(this);
    }
}
