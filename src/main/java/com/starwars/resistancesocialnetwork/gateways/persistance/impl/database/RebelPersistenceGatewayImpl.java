package com.starwars.resistancesocialnetwork.gateways.persistance.impl.database;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities.RebelEntity;
import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.mappers.RebelEntityMapper;
import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.repositories.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RebelPersistenceGatewayImpl implements RebelPersistenceGateway {

    private final RebelRepository rebelRepository;
    private final RebelEntityMapper rebelEntityMapper = RebelEntityMapper.INSTANCE;

    @Override
    public Rebel save(Rebel rebel, Headquarter headquarter) {
        RebelEntity rebelEntity = rebelEntityMapper.toEntity(rebel, headquarter);
        RebelEntity saved = rebelRepository.save(rebelEntity);
        return rebelEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Rebel> findById(Long id) {
        Optional<RebelEntity> found = rebelRepository.findById(id);
        return found.map(rebelEntityMapper::toDomain);
    }

    @Override
    public boolean existById(Long id) {
        return rebelRepository.existsById(id);
    }

    @Override
    public Page<Rebel> findAll(Pageable page) {
        Page<RebelEntity> found = rebelRepository.findAll(page);
        return new PageImpl<>(found.getContent().stream()
                    .map(rebelEntityMapper::toDomain)
                    .collect(Collectors.toList()));
    }

    @Override
    public void deleteById(Long id) {
        rebelRepository.deleteById(id);
    }
}
