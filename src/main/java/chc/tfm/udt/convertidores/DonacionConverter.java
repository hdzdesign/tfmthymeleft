package chc.tfm.udt.convertidores;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.ItemDonacion;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Converter
@Component(value = "donacionConverter")
public class DonacionConverter implements AttributeConverter<Donacion, DonacionEntity> {

    @Autowired
    @Qualifier("itemDonacionConverter")
    private ItemDonacionConverter itemConverter;

    @Autowired
    @Qualifier("jugadorConverter")
    private JugadorConverter jugadorConverter;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    //Convertir de DTO a ENTITY
    @Override
    public DonacionEntity convertToDatabaseColumn(Donacion attribute) {
        LOG.info("CONVERTIDOR ENTITY A DTO");
        DonacionEntity entity = new DonacionEntity();
        entity.setId(attribute.getId());
        entity.setObservacion(attribute.getObservacion());
        entity.setCreateAt(attribute.getCreateAt());
        //SET JUGADOR
        Jugador jugador = attribute.getJugador();
        entity.setJugadorEntity(jugador != null ?
                                jugadorConverter.convertToDatabaseColumn(jugador)
                                : new JugadorEntity());

        //SET LIST
        List<ItemDonacion> items = attribute.getItems();
        entity.setItems(items != null
                        ? items
                        .stream()
                        .map(dto -> itemConverter.convertToDatabaseColumn(dto))
                        .collect(Collectors.toList())
                        : new ArrayList<>());
        LOG.info("SE HA SETEADO BIEN LA DONACIÓN Y EL JUGADOR.");
       // LOG.info(entity.toString());
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
        //seteo al jugador lo que viene de la donación
        JugadorEntity jugadorEntity = dbData.getJugadorEntity();
        dto.setJugador(jugadorEntity != null ?
                jugadorConverter.convertToEntityAttribute(jugadorEntity)
                : new Jugador());
        // SETEAMOS LA LISTA DE ITEMS
        List<ItemDonacionEntity> itemDonaciones = dbData.getItems();
        dto.setItems(itemDonaciones != null
                    ? itemDonaciones.stream().map(item -> itemConverter.convertToEntityAttribute(item))
                    .collect(Collectors.toList())
                    : new ArrayList<>());


        LOG.info("Fin convertidor DONCACION");
       // LOG.info(dto.toString());
        return dto;
    }
}
