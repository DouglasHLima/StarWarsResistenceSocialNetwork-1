package com.starwars.resistancesocialnetwork.gateways.persistance.impl.database;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities.HeadquarterEntity;
import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.mappers.HeadquarterEntityMapper;
import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.repositories.HeadQuarterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HeadQuartersPersistenceGatewayImpl implements HeadQuartersPersistenceGateway {
    private final HeadQuarterRepository headQuarterRepository;
    private final HeadquarterEntityMapper headquarterEntityMapper = HeadquarterEntityMapper.INSTANCE;

    @Override
    public Headquarter save(Headquarter headquarter) {
        HeadquarterEntity headquarterEntity = headquarterEntityMapper.toEntity(headquarter);
        headquarterEntity.getRebels().forEach(rebel -> rebel.setLocation(headquarterEntity));
        HeadquarterEntity saved = headQuarterRepository.save(headquarterEntity);
        return headquarterEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Headquarter> findById(Long id) {
        Optional<HeadquarterEntity> found = headQuarterRepository.findById(id);
        return found.map(headquarterEntityMapper::toDomain);
    }

    @Override
    public boolean existById(Long id) {
    return headQuarterRepository.existsById(id);
    }

    @Override
    public Page<Headquarter> findAll(Pageable page) {
        Page<HeadquarterEntity> found = headQuarterRepository.findAll(page);
        return new PageImpl<>(found.getContent().stream()
                .map(headquarterEntityMapper::toDomain)
                .collect(Collectors.toList()));
    }

    @Override
    public void deleteById(Long id) {
        headQuarterRepository.deleteById(id);
    }
}
