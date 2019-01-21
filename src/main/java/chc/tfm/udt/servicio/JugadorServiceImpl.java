package chc.tfm.udt.servicio;
import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.DTO.Producto;
import chc.tfm.udt.convertidores.DonacionConverter;
import chc.tfm.udt.convertidores.JugadorConverter;
import chc.tfm.udt.convertidores.ProductoConverter;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.entidades.ProductoEntity;
import chc.tfm.udt.repositorios.IDonacionDAO;
import chc.tfm.udt.repositorios.IProductoDAO;
import chc.tfm.udt.repositorios.JugadorDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Clase que usaremos para seguir el patron de diseño "Business Service Facade"
 */
@Service(value = "jugadorServiceImpl")
public class JugadorServiceImpl implements IJugadorService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private JugadorConverter converter;

    private DonacionConverter donacionConverter;

    private JugadorDAO jugadorDAO;

    private IProductoDAO productoDAO;

    private IDonacionDAO donacionDAO;

    private ProductoConverter productoConverter;

    @Autowired
    public JugadorServiceImpl(@Qualifier(value = "jugadorDAO") JugadorDAO jugadorDAO,
                              @Qualifier(value = "jugadorConverter") JugadorConverter converter,
                              @Qualifier(value = "iProductoDAO") IProductoDAO productoDAO,
                              @Qualifier(value = "iDonacionDAO") IDonacionDAO donacionDAO,
                              @Qualifier(value = "donacionConverter") DonacionConverter donacionConverter,
                              @Qualifier(value = "productoConverter") ProductoConverter productoConverter){
        this.jugadorDAO = jugadorDAO;
        this.converter = converter;
        this.productoDAO = productoDAO;
        this.donacionDAO = donacionDAO;
        this.donacionConverter = donacionConverter;
        this.productoConverter = productoConverter;

    }

    @Override
    @Transactional(readOnly = true)
    public List<JugadorEntity> findAll() {
        return jugadorDAO.findAll();
    }

    @Override
    public Page<JugadorEntity> findAll(Pageable pageable) {
        return jugadorDAO.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Jugador findOne(Long id) {
        Jugador resultado = null;

        Optional<JugadorEntity> buscar = jugadorDAO.findById(id);
        if(buscar.isPresent()){
            JugadorEntity encontrado = buscar.get();
            resultado = converter.convertToEntityAttribute(encontrado);
        }

        return resultado;
    }


    @Override

    public Jugador save(Jugador jugador) {
        LOG.info("Entramos en el Servicio SAVE.");
        JugadorEntity j = converter.convertToDatabaseColumn(jugador);
        JugadorEntity saved = jugadorDAO.save(j);
        Jugador returned = converter.convertToEntityAttribute(saved);
        return returned;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        jugadorDAO.deleteById(id);

    }
    @Transactional(readOnly = true)
    @Override
    public List<ProductoEntity> findByNombre(String term) {
        return productoDAO.findByNombreLikeIgnoreCase("%"+term+"%");
    }

    @Override
    public Donacion saveDonacion(Donacion donacion) {
        DonacionEntity d = donacionConverter.convertToDatabaseColumn(donacion);
        DonacionEntity saved = donacionDAO.save(d);
        Donacion returned = donacionConverter.convertToEntityAttribute(saved);
        return returned;
    }

    @Transactional(readOnly = true)
    @Override
    public Producto findProductoEntityById(Long id) {
        Producto resultado = null;
        Optional<ProductoEntity> buscar = productoDAO.findById(id);
        if(buscar.isPresent()){
            ProductoEntity encontrado = buscar.get();
            resultado = productoConverter.convertToEntityAttribute(encontrado);
        }
        return resultado;
    }
}
