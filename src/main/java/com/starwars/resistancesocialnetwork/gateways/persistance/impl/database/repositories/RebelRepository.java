package com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.repositories;

import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities.RebelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelRepository extends JpaRepository<RebelEntity, Long> {}
