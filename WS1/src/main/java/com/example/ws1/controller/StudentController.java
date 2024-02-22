package com.example.ws1.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.ws1.mo.Student;
import org.apache.coyote.Request;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.logging.Logger;

@RestController
public class StudentController {

    // Logger che mi permette di stampare dele cose
    // private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    /** "db" fake fatto da una HashMap, con chiave int e valore oggetto Student */
    public HashMap<Integer, Student> studentsDB = new HashMap<>();


    /**
     * @return La mia HashMap, che viene tradotta in automatico da Spring, (e dalle librerie che utilizza) in JSON
     */
    @RequestMapping(value = "/students", // URL: "/students" (students è una collection)
                    method = RequestMethod.GET, // mappo l'URL con il metodo GET (chiamo la collection in GET)
                    produces = MediaType.APPLICATION_JSON_VALUE // (la collection ritorna un JSON)
                    )
    public HashMap<Integer, Student> getStudents()
    {
        return studentsDB;
    }


    /** Recypera la determinata risorsa dalla collezione "students" che ha quel determinato id */
    @RequestMapping(value = "/students/{id}", // Nell'URL, dopo students ho la variabile id, che rappresenta l'id dello studente
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE
                    )
    public Student getStudent( @PathVariable("id") int id) { // Per mappare la variabile id che sta dentro al path con la mia variabile id intera Java uso l'ann. @PathVariable
        System.out.println(String.valueOf(id)); // loggo l'id che ho richiesto
        Student student = studentsDB.get(id); // Leggo lo studente che ha quell'id dallo studentsDB
        return student; // ritorno l'oggetto student
    }



    /** Inserimento di un nuovo studente */
    @RequestMapping(value ="/students",
                    method = RequestMethod.POST, // Uso il metodo POST sulla collection ==> perchè voglio aggiungere un valore
                    consumes = MediaType.APPLICATION_JSON_VALUE, // che passo come JSON (passo lo studente in formato JSON)
                    produces = MediaType.APPLICATION_JSON_VALUE // e produco un JSON di risposta (che è lo studente che ho appena creato)
                    )
    @ResponseStatus( HttpStatus.CREATED ) // Annotation per dire che questo mapping ritorna come codice stato HTTP il codice 201 created ==> qui overrido il default (200) e voglio un 201 created
    public Student addStudent(@RequestBody Student student) { // @RequestBody converte lo Student dal body della request in un oggetto Java in automatico
        // Aggiungo in db
        studentsDB.put(student.getId(), student);
        System.out.println("Student " + student.getId()+ " added"); // log a console
        return student; // Ritorno sempre in Json lo stesso studente che ho inserito
    }


    /** Eliminazione di un determinato studente dalla collezione */
    @RequestMapping(value = "/students/{id}",
                    method = RequestMethod.DELETE )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteStudent( @PathVariable("id") int id ) {
        Student student = studentsDB.remove(id);
        System.out.println("Student " + id + " deleted");
    }


}
