package org.acme.quarkus.repository;
import org.acme.entities.Ordrar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdrarRepository extends JpaRepository<Ordrar,Integer>
{
    List<Ordrar> findAllByKundId_Id(Integer id);

    List<Ordrar> findAllByStugId_Id(Integer id);
}



