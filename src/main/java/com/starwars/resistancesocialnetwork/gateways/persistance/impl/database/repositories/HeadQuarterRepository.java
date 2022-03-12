package com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.repositories;

import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities.HeadquarterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadQuarterRepository extends JpaRepository<HeadquarterEntity, Long> {
}
