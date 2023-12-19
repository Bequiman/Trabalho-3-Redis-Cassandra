package com.iftm.cassandraredis.controllers;

import com.iftm.cassandraredis.entities.dto.ImovelDTO;
import com.iftm.cassandraredis.services.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/imovel")
public class ImovelController {
    @Autowired
    ImovelService imovelService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")UUID id){
        return imovelService.delete(id);
    }


    @GetMapping
    public List<ImovelDTO> findAll() {
        return imovelService.findAll();
    }

    @GetMapping("/{id}")
    public ImovelDTO findById(@PathVariable("id")UUID id) {
        return imovelService.findById(id);

    }
    @PostMapping
    public ResponseEntity<ImovelDTO> save(@RequestBody ImovelDTO imovelDTO) {
        return imovelService.save(imovelDTO);
    }

    @PutMapping
    public ResponseEntity<ImovelDTO> update(@RequestBody ImovelDTO imovelDTO){
        return imovelService.update(imovelDTO);
    }
}


