package com.iftm.cassandraredis.services;

import com.iftm.cassandraredis.entities.Imovel;
import com.iftm.cassandraredis.entities.dto.ImovelDTO;
import com.iftm.cassandraredis.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImovelService {
    @Autowired
    ImovelRepository imovelRepository;
    @CacheEvict(value = {"all-imovel", "imovel"}, allEntries = true)
    public ResponseEntity<?> delete(UUID id){
        if (id == null ){
            return ResponseEntity.notFound().build();
        }

        if (!imovelRepository.existsById(id)){
            return ResponseEntity.badRequest().build();
        }

        imovelRepository.deleteById(id);

        if (imovelRepository.existsById(id)){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.noContent().build();
    }
    @Cacheable(cacheNames = "all-imovel")
    public List<ImovelDTO> findAll() {
        var dbImovel = imovelRepository.findAll();

        var dbImovelDTO = dbImovel.stream().map(imovel -> {
            return new ImovelDTO(imovel);
        }).collect(Collectors.toList());

        return dbImovelDTO;
    }
    @Cacheable(cacheNames = "imovel", key = "#id")
    public ImovelDTO findById(UUID id) {
        if (id == null) {
            throw new RuntimeException("Id nulo");
        }
        var dbImovel = imovelRepository.findById(id).orElseThrow(() -> new RuntimeException("Não há registros para este ID."));
        return new ImovelDTO(dbImovel);
    }
    @CacheEvict(value = {"all-imovel", "imovel"}, allEntries = true)
    public ResponseEntity<ImovelDTO> save(ImovelDTO imovelDTO) {
        if (imovelDTO == null) {
            return ResponseEntity.notFound().build();
        }
        var dbImovelDTO = new ImovelDTO(imovelRepository.save(imovelDTO.toImovel()));
        return ResponseEntity.ok(dbImovelDTO);
    }
    @CacheEvict(value = {"all-imovel", "imovel"}, allEntries = true)
    public ResponseEntity<ImovelDTO> update(ImovelDTO imovelDTO) {
        if (imovelDTO == null) {
            return ResponseEntity.notFound().build();
        }
        Optional<Imovel> dbImovel = imovelRepository.findById(imovelDTO.getId());
        if (dbImovel == null || dbImovel.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Imovel updateImovel = dbImovel.get();
        updateImovel.setEndereco(imovelDTO.getEndereco());
        updateImovel.setTipoImovel(imovelDTO.getTipoImovel());
        updateImovel.setArea(imovelDTO.getArea());
        updateImovel.setPreco(imovelDTO.getPreco());

        //ImovelDTO dbImovelDTO = new ImovelDTO(imovelRepository.save(updateImovel));
        //ResponseEntity.ok(dbImovelDTO);

        return ResponseEntity.ok(new ImovelDTO(imovelRepository.save(updateImovel)));
    }
}


