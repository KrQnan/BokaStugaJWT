package org.acme.quarkus.repository;

import org.acme.entities.Stuga;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StugRepository extends JpaRepository<Stuga,Integer>
{
}
