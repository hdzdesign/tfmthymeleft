package chc.tfm.udt.convertidores;

import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.JugadorEntity;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Component(value = "jugadorConverter")
public class JugadorConverter implements AttributeConverter<JugadorEntity, Jugador> {

    //Convierte de Entity a DTO
    @Override
    public Jugador convertToDatabaseColumn(JugadorEntity attribute) {
        Jugador j = new Jugador();
        j.setId(attribute.getId());
        j.setNombre(attribute.getNombre());
        j.setApellido1(attribute.getApellido1());
        j.setApellido2(attribute.getApellido2());
        j.setTelefono(attribute.getTelefono());
        j.setMail(attribute.getMail());
        j.setNacimiento(attribute.getNacimiento());
        j.setEdad(attribute.getEdad());
        j.setNacionalidad(attribute.getNacionalidad());
        j.setInscripcion(attribute.getInscripcion());
        j.setDorsal(attribute.getDorsal());
        j.setFoto(attribute.getFoto());
        j.setDonaciones(attribute.getDonaciones());
        return j;
    }
    //Convierte de DTO a Entity
    @Override
    public JugadorEntity convertToEntityAttribute(Jugador dbData) {
        JugadorEntity entity = new JugadorEntity();
        entity.setId(dbData.getId());
        entity.setNombre(dbData.getNombre());
        entity.setApellido1(dbData.getApellido1());
        entity.setApellido2(dbData.getApellido2());
        entity.setTelefono(dbData.getTelefono());
        entity.setMail(dbData.getMail());
        entity.setNacimiento(dbData.getNacimiento());
        entity.setEdad(dbData.getEdad());
        entity.setNacionalidad(dbData.getNacionalidad());
        entity.setInscripcion(dbData.getInscripcion());
        entity.setDorsal(dbData.getDorsal());
        entity.setFoto(dbData.getFoto());
        entity.setDonaciones(dbData.getDonaciones());
        return entity;
    }
}
