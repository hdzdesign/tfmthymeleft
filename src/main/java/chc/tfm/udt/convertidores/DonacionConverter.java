package chc.tfm.udt.convertidores;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.ItemDonacion;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Collectors;

@Converter
@Component(value = "donacionConverter")
public class DonacionConverter implements AttributeConverter<Donacion, DonacionEntity> {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    //Convertir de DTO a ENTITY
    @Override
    public DonacionEntity convertToDatabaseColumn(Donacion attribute) {
        LOG.info("CONVERTIDOR ENTITY A DTO");
        DonacionEntity entity = new DonacionEntity();
        entity.setId(attribute.getId());
        entity.setObservacion(attribute.getObservacion());
        entity.setCreateAt(attribute.getCreateAt());
        entity.setJugadorEntity(new JugadorEntity(attribute.getJugador()));
        entity.setItems(attribute.getItems().
                stream().
                map(d -> new ItemDonacionEntity(d)).
                collect(Collectors.toList()));
        LOG.info("Fin convertidor");
        return entity;
    }
    //Convertir de ENTITY a DTO
    @Override
    public Donacion convertToEntityAttribute(DonacionEntity dbData) {
        Donacion dto = new Donacion();
        LOG.info("CONVERTIDOR DTO A ENTITY");
        dto.setId(dbData.getId());
        dto.setObservacion(dbData.getObservacion());
        dto.setCreateAt(dbData.getCreateAt());
        dto.setJugador(new Jugador(dbData.getJugadorEntity()));
        dto.setItems(dbData.getItems().
                stream().
                map(o -> new ItemDonacion(o)).
                collect(Collectors.toList()));
        LOG.info("Fin convertidor DONCACION");
        return dto;
    }
}
