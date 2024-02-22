package com.psw.wssource.repository;

import com.psw.wssource.mo.CustomerorderEntity;
import com.psw.wssource.mo.CustomerorderrowEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/** Qui, invece di estendere CrudRepository estendo PagingAndSortingRepository (che è figlio del CrudRepository)
 *  che mi consente anche di fare paginazione e ordinamento all'interno dei miei metodi del DAO */
public interface CustomerorderRepository extends PagingAndSortingRepository<CustomerorderEntity, Integer> {

    Iterable<CustomerorderEntity> findAll();

    /** Aggiungo dei metodi che non ho implementato (li implementa da soli) TODO: è sufficiente seguire un formato nella scrittura del metodo  */

    /** "findAll"=select di tutti i record "ByExportTimestamp"=basandosi sull'ExportTimestamp "IsNull"=nullo
     *  ==> Questa findAll prende tutti gli ordini della tabella customerorder che hanno exportTimestamp a null.
     *  Ed è il metodo che mi serve per dare al mio sistema utilizzatore l'elenco degli ordini che non sono ancora stati marcati
     *  con il timestamp
     * */
    List<CustomerorderEntity> findAllByExportTimestampIsNull();


    /** Questa è una copia identica del metodo precedente, che però è paginabile: cioè mi consente di estrarne solo una parte (per non
     *  prenderli tutti in una volta) Es) prendimi solo i primi k */
    List<CustomerorderEntity> findAllByExportTimestampIsNull(Pageable pageable);


    /** Questo metodo contiene una query scritta ad hoc, non in SQL, ma TODO: in jpql, linguaggio con cui si scrivono le query in Spring:
     *  ==> faccio una UPDATE nella tabella customerorder (che chiamo con l'alias o), e setto il timestamp alla data e ora attuali, quando
     *      l'orderIds sta in una lista che è il parametro che mi viene da fuori.
     *      <br/>
     *      Questo parametro è una collection di orderIds ==> a questo metodo passerò una Collection di Integer, che sono gli id degli ordini,
     *      e lui va a marcare come esportati quegli specifici id.
     *      <br/>
     *      Praticamente faccio una update non sulla tabella, ma sulla entity, poi ci pensa Hibernate a tradurla in tabella
     */
    @Modifying
    @Query("update CustomerorderEntity o set o.exportTimestamp=current_timestamp where o.orderId in (:orderIds)")
    int markExported(@Param("orderIds")Collection<Integer> orderIds);
}
