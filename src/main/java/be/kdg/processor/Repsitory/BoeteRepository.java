package be.kdg.processor.Repsitory;

import be.kdg.processor.model.Boete;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoeteRepository extends CrudRepository<Boete, Long> {

   List<Boete> findByNationaalNr(String nationaalNr);

//    @Query(value = "SELECT b FROM Boete WHERE b.overtredingsType=?1")
//    List<Boete> findEmissieBoeteDriver(String overtredingsType);
}
