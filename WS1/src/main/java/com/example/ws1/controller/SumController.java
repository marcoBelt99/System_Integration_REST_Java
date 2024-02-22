package com.example.ws1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // questa annotazione fa capire a Spring che questa classe è un Controller Rest.
public class SumController {


    /** Mappo l'url "/sum" con il metodo GET.
     *  TODO: Potevo scrivere come shortcut anche: @GetMapping("value="sum") e ottenevo lo stesso
     *  risultato di questo @RequestMapping(...)
     *
     * Questo controller si attiva quando arriva una richiesta HTTP con "/sum" come URL, con il metodo GET
     *  */
    @RequestMapping(value="/sum", method = RequestMethod.GET) // analogo
    public Integer sum(
        /** Questo metodo si piglia due parametri, "i" e "j", che sono due stringhe, che hanno come default value 0 */
        @RequestParam(value = "i", defaultValue = "0") String i,
        @RequestParam(value = "j", defaultValue = "0") String j
    )
    {
        return Integer.parseInt(i) + Integer.parseInt(j);
    }
}

/**
 * Prove:
 * - http://localhost:8080/sum?i=2&j=9  ==> 11
 * - http://localhost:8080/sum ==> ritorna 0
 * Se provo con il POST e non l'ho implementato, mi dice "Method not allowed"
 * */
