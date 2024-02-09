package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.AbrigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService service;

    @GetMapping
    public ResponseEntity<List<AbrigoDto>> listar() {
        List<Abrigo> abrigos = service.listar();
        List<AbrigoDto> abrigoDTOs = abrigos.stream()
                .map(abrigo -> new AbrigoDto(abrigo.getId(), abrigo.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(abrigoDTOs);
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<CadastrarPetDto>> listarPets(@PathVariable String idOuNome) {
        if (idOuNome == null || idOuNome.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            List<Pet> pets = service.listarPets(idOuNome);
            List<CadastrarPetDto> petDTOs = pets.stream()
                    .map(pet -> new CadastrarPetDto(pet.getId(), pet.getNome(), pet.getTipo(), pet.isAdotado(), pet.getAbrigo().getId()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(petDTOs);
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/pets")
    public ResponseEntity<Void> cadastrarPets(@RequestBody CadastrarPetDto dto) {
        try {
            service.cadastrarPets(dto);
            return ResponseEntity.ok().build();
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

