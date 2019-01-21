package chc.tfm.udt.convertidores;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import com.google.gson.Gson;
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
@Component(value = "jugadorConverter")
public class JugadorConverter implements AttributeConverter<Jugador, JugadorEntity> {

    @Autowired
    @Qualifier("donacionConverter")
    private DonacionConverter donacionConverter;

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    //Convierte de DTO a Entity
    @Override
    public JugadorEntity convertToDatabaseColumn(Jugador attribute) {
    LOG.info("Entramos en el convertidor de DTO A ENTITY");
        JugadorEntity entity = new JugadorEntity();

        entity.setId(attribute.getId());
        entity.setNombre(attribute.getNombre());
        entity.setApellido1(attribute.getApellido1());
        entity.setApellido2(attribute.getApellido2());
        entity.setTelefono(attribute.getTelefono());
        entity.setMail(attribute.getMail());
        entity.setDni(attribute.getDni());
        entity.setNacimiento(attribute.getNacimiento());
        entity.setEdad(attribute.getEdad());
        entity.setNacionalidad(attribute.getNacionalidad());
        entity.setInscripcion(attribute.getInscripcion());
        entity.setDorsal(attribute.getDorsal());
        entity.setFoto(attribute.getFoto());
        List<Donacion> donaciones = attribute.getDonaciones();
        LOG.info(new Gson().toJson(donaciones));
        entity.setDonaciones(donaciones != null
                ? donaciones
                .stream()
                .map(j -> this.donacionConverter.convertToDatabaseColumn(j))
                .collect(Collectors.toList())
                : new ArrayList<>());
        LOG.info("Todo OK");
        return entity;

    }
    //Convierte de Entity a DTO
    @Override
    public Jugador convertToEntityAttribute( JugadorEntity dbData) {

        Jugador j = new Jugador();

        j.setId(dbData.getId());
        j.setNombre(dbData.getNombre());
        j.setApellido1(dbData.getApellido1());
        j.setApellido2(dbData.getApellido2());
        j.setTelefono(dbData.getTelefono());
        j.setMail(dbData.getMail());
        j.setDni(dbData.getDni());
        j.setNacimiento(dbData.getNacimiento());
        j.setEdad(dbData.getEdad());
        j.setNacionalidad(dbData.getNacionalidad());
        j.setInscripcion(dbData.getInscripcion());
        j.setDorsal(dbData.getDorsal());
        j.setFoto(dbData.getFoto());
        List<DonacionEntity> donaciones = dbData.getDonaciones();
        j.setDonaciones(donaciones != null
                ? donaciones
                .stream()
                .map(d ->this.donacionConverter.convertToEntityAttribute(d))
                .collect(Collectors.toList())
                : new ArrayList<>());
       // LOG.info(j.toString());
        return j;
    }
}
