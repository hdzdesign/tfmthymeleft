package chc.tfm.udt.Controller;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.ItemDonacion;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.DTO.Producto;
import chc.tfm.udt.convertidores.DonacionConverter;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.entidades.ProductoEntity;
import chc.tfm.udt.servicio.IJugadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
/**
 * Clase controladora de las donaciones destinadas a los jugadores
 *
 */

@Controller
@RequestMapping("/donacion")
@SessionAttributes("donacion")
public class DonacionController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private IJugadorService jugadorService;
    private DonacionConverter donacionConverter;

    @Autowired
    public DonacionController(@Qualifier(value = "donacionConverter") DonacionConverter donacionConverter,
                              @Qualifier(value = "jugadorServiceImpl") IJugadorService jugadorService){
        this.donacionConverter = donacionConverter;
        this.jugadorService = jugadorService;

    }

    /**
     * Con este metodo vamos a comunicarnos con el formulario que va hacer posible la inserción en base de datos de los
     * distintos productos que van a estar asociados a un jugador.
     * @param jugadorEntityId La id del jugador al que va a estar asociada la donación por parte del club.
     * @param model Objeto de tipo MAP , para pasar a la vista los distintos objetos de la aplicación
     * @param push Objeto que utilizaremos para mostar al usuario mensajes de información
     * @return
     */
    @GetMapping("/form/{jugadorEntityId}")
    public String crear(@PathVariable(  value = "jugadorEntityId")
                                        Long jugadorEntityId,
                                        Map<String, Object> model,
                                        RedirectAttributes push){

        Jugador j = jugadorService.findOne(jugadorEntityId);

        if(j == null){
            push.addFlashAttribute("error", "El jugador no existe en la base de datos");
            return "redirect:/listar";
        }
        Donacion donacion = new Donacion();
        donacion.setJugador(j);

        model.put("donacion", donacion);
        model.put("titulo", "Crear Donacion.");

        return "donacion/form";
    }
    // tiene un pathVariable , que sería el texto , {term}
    // produces , salida del tipo json
    // @ResponseBody Suprime el cargar una vista de thymeleaft , en vez de eso , toma el return converido a json y lo va a poner en el body de la respuesta.

    /**
     * Metodo que utilizaremos para controlar la devolución de la base de datos de los productos asociados a un jugador.
     * @GetMapping : Mapeamos la misma url que definimos en la llamada AJAX al servidor, pasando como parametro el texto
     * @ResponseBody: Con esta anotación elminamos la carga de la vista con http://www.thymeleaf.org, en vez de eso,
     *                Retornamos convertido a Json para mostrarlo en el body de la respuesta.
     * @PathVariable: La variable que manejamos es el texto que se introduce en el imput de busqueda.
     * @param term
     * @return
     */
    @GetMapping (value = "/cargar-productos/{term}", produces = { "application/json" })
    public @ResponseBody List<ProductoEntity> cargarProducto(@PathVariable String term){
        return jugadorService.findByNombre(term);
    }


    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("donacion") Donacion donacion,
                          @RequestParam(name = "item_id[]",required = false) Long[] itemId,
                          @RequestParam(name = "cantidad[]",required = false) Integer[] cantidad,
                          RedirectAttributes push,
                          SessionStatus status) {
        for (int i = 0; i < itemId.length; i++ ){
            Producto producto = jugadorService.findProductoEntityById(itemId[i]);

            ItemDonacion linea = new ItemDonacion();
            linea.setCantidad(cantidad[i]);
            linea.setProducto(producto);
            donacion.addItemDonacion(linea);

            log.info("ID " + itemId[i].toString() + ", cantidad " + cantidad[i].toString());
        }
        jugadorService.saveDonacion(donacion);
        status.setComplete();
        push.addFlashAttribute("success", "La Donación ha sido asignada con exito");
        log.info("ESTA ES LA DONACIÓN CON EL JUGADOR ASOCIADO Y SU PRODUCTO");
        return "redirect:/ver/" + donacion.getJugador().getId();
    }


}