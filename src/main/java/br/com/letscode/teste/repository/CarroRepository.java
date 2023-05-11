package br.com.letscode.teste.repository;

import br.com.letscode.teste.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    @Query("SELECT a FROM Carro a JOIN FETCH a.classe c")
    List<Carro> findWithClasses();

    @Query("SELECT c FROM Carro c WHERE c.uid = :uid")
    List<Carro> findByUid(@Param("uid") String uid);

}
