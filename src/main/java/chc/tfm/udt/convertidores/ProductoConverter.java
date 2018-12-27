package chc.tfm.udt.convertidores;

import chc.tfm.udt.DTO.Producto;
import chc.tfm.udt.entidades.ProductoEntity;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Component(value = "productoConverter")
public class ProductoConverter implements AttributeConverter<Producto, ProductoEntity> {


    @Override
    public ProductoEntity convertToDatabaseColumn(Producto attribute) {

        ProductoEntity entity = new ProductoEntity();
        entity.setId(attribute.getId());
        entity.setNombre(attribute.getNombre());
        entity.setPrecio(attribute.getPrecio());
        entity.setCreateAt(attribute.getCreateAt());

        return entity;
    }

    @Override
    public Producto convertToEntityAttribute(ProductoEntity dbData) {
        Producto dto = new Producto();

        dto.setId(dbData.getId());
        dto.setNombre(dbData.getNombre());
        dto.setPrecio(dbData.getPrecio());
        dto.setCreateAt(dbData.getCreateAt());

        return dto;
    }
}
