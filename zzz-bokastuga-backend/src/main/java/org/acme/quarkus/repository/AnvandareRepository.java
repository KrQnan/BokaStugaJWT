package org.acme.quarkus.repository;

import org.acme.entities.Anvandare;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnvandareRepository extends JpaRepository<Anvandare, Integer>
{
    Anvandare findByEmailAndPassword(String email, String password);

    Anvandare findByEmail(String name);
}
