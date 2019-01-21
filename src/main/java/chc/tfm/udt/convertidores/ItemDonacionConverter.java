package chc.tfm.udt.convertidores;

import chc.tfm.udt.DTO.ItemDonacion;
import chc.tfm.udt.DTO.Producto;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.entidades.ProductoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Component(value = "itemDonacionConverter")
public class ItemDonacionConverter implements AttributeConverter<ItemDonacion, ItemDonacionEntity> {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("productoConverter")
    private ProductoConverter productoConverter;

    @Override
    public ItemDonacionEntity convertToDatabaseColumn(ItemDonacion attribute) {
    ItemDonacionEntity entity = new ItemDonacionEntity();
        entity.setId(attribute.getId());
        entity.setCantidad(attribute.getCantidad());
        entity.setProductoEntity(productoConverter.convertToDatabaseColumn(attribute.getProducto()));
        LOG.info("SE haconvertido correctamentE EL ITEMDTO A ITEMENTITY");
       // LOG.info(entity.toString());
        return entity;
    }

    @Override
    public ItemDonacion convertToEntityAttribute(ItemDonacionEntity dbData) {
        ItemDonacion dto = new ItemDonacion();
        dto.setId(dbData.getId());
        dto.setCantidad(dbData.getCantidad());
        dto.setProducto(productoConverter.convertToEntityAttribute(dbData.getProductoEntity()));
        LOG.info("SE HA CONVERTIDO COORRECTAMENTE EL ITEMENTITY A ITEMDTO");
       // LOG.info(dto.toString());
        return dto;
    }
}

