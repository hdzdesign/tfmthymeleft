package chc.tfm.udt.convertidores;

import chc.tfm.udt.DTO.ItemDonacion;
import chc.tfm.udt.DTO.Producto;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.entidades.ProductoEntity;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Component(value = "itemDonacionConverter")
public class ItemDonacionConverter implements AttributeConverter<ItemDonacion, ItemDonacionEntity> {

    @Override
    public ItemDonacionEntity convertToDatabaseColumn(ItemDonacion attribute) {
    ItemDonacionEntity entity = new ItemDonacionEntity();
        entity.setId(attribute.getId());
        entity.setCantidad(attribute.getCantidad());
        entity.setProductoEntity(new ProductoEntity(attribute.getProducto()));
        return entity;
    }

    @Override
    public ItemDonacion convertToEntityAttribute(ItemDonacionEntity dbData) {
        ItemDonacion dto = new ItemDonacion();
        dto.setId(dbData.getId());
        dto.setCantidad(dbData.getCantidad());
        dto.setProducto(new Producto(dbData.getProductoEntity()));
        return dto;
    }
}

