package com.psw.wssource.controller;

import com.psw.wssource.mo.CustomerorderEntity;
import com.psw.wssource.repository.CustomerorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController // Dico a Spring che questo è un controller che crea dei Web Services REST
@RequestMapping( value = "/wssource") // TODO: attento: questo è un RequestMapping a livello di classe ==> tutti i RequestMapping sotto, stanno
                  //  TODO: dentro a questo path
public class OrdersController {

    @Autowired // spring usa l'IoC (Inversion of Control), e quindi mi istanzia in automatico il repository dei customerorder
    private CustomerorderRepository customerorderRepository;

    /** Ritorna la collection completa degli orders */
    @RequestMapping(value = "/orders", // Tipico URL di una collection (collection degli ordini)
                    method = RequestMethod.GET, // Restituiscimi la collection completa
                    produces = MediaType.APPLICATION_JSON_VALUE) // Mi aspetto un JSON
    public Iterable<CustomerorderEntity> getCustomerOrders() {
        Iterable<CustomerorderEntity> customerOrders = customerorderRepository.findAll();
        return customerOrders;
    }


    /** Ritorna la collection di tutti gli orders, ma solo quelli non esportati.<br/>
     *  Mi prendo il parametro "limit", che mi dice quanti valori devo prendere.
     *  <br/>
     * @return Numero di ordini non ancora esportati secondo il "limit" che gli ho dato.
     *  */
    @RequestMapping(value = "/orders/notExported",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerorderEntity> getCustomerOrdersNotExported(@RequestParam(value = "limit", required = false) Integer limit) {
        List<CustomerorderEntity> customerOrdersNotExported;

        if(limit != null) { // se mi è stato passato qualcosa
            // Ritornami i primi limit (da 1 a limit) orders. Es) se limit=10, i primi dal 1° a 10° orders
            // Se avessi messo PageRequest.of(1, limit) mi avrebbe dato da 11 a limit; Es) dal 11° al 20° orders
            // PageRequest.of(2, limit) Es) dal 21° a 30°; ecc.
            Pageable firstTwoElements = PageRequest.of(0, limit);
            customerOrdersNotExported=  customerorderRepository.findAllByExportTimestampIsNull(firstTwoElements);
        } else {
            customerOrdersNotExported=  customerorderRepository.findAllByExportTimestampIsNull();
        }
        return customerOrdersNotExported;
    }



    /** POST su una collection per marcare la colonna exportTimestamp della tabella customerorder. */
    @RequestMapping(value = "/orders/markExported",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE) // Questo metodo consuma (si aspetta di ricevere) una struttura JSON
    @ResponseStatus( HttpStatus.NO_CONTENT ) // NO_CONTENT = 204
    @Transactional  // Ho una query di modifica, quindi la inserisco dentro una transazione in odo che se c'è qualcosa che non funziona viene fatto
                    // il rollback di tutti i metodi transazionali che ho dichiarato
    public void markExported(@RequestBody Collection<Integer> OrdersIds) { // Nel body della request (passato in POST) si prende il JSON che poi viene convertito nella Collection di integer OrdersIds
        int affected = customerorderRepository.markExported(OrdersIds); // marco come esportati gli ordini che mi sono stati inviati
        System.out.println(String.valueOf(affected)); // stampo quanti record ho cambiato
    }


}
