package com.psw.wssource.repository;

import com.psw.wssource.mo.CustomerorderrowEntity;
import org.springframework.data.repository.CrudRepository;

/** Classe che, l'unica cosa che fa è estendere la superclasse (interfaccia) CrudRepository, che mi consente
 *  di fare: create, read, update, delete (il repository standard) a cui passo la CustomerorderEntity e Integer.
 *  Così facendo ho già tutti i metodi standard per l'accesso alla tabella: findAll(), insert(), delete(), etc.
 *  */
public interface CustomerorderrowRepository extends CrudRepository<CustomerorderrowEntity, Integer> {
}
