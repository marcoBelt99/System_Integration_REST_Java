package com.example.ws1.controller;

import com.example.ws1.mo.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private final AtomicLong counter = new AtomicLong(); // (un AtomicLong è un long che può essere modificato da più processi contemporaneamente)

    /** Ho aggiunto l'opzione produces, con la quale dico che tipo di formato deve essere prodotto da Spring quando rispondo con questo WS.
     *  Esplicito il fatto che venga prodotto un JSON
     *  */
    @RequestMapping(value="/greeting", method = RequestMethod.GET, produces = "application/json")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) { // come parametro si prende un nome

        // Creo un oggetto Greeting(inizializzato in quel modo) e lo ritorno
        return new Greeting(counter.incrementAndGet(), "Hello, " + name);
    }
}
