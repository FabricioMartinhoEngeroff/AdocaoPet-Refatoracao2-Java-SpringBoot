package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.controller.PetController;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    @Autowired
    private PetController petController;

    public List<PetDto> listarTodosDisponiveis() {
        List<Pet> pets = repository.findAll();
        return pets.stream()
                .filter(pet -> !pet.getAdotado())
                .map(pet -> new PetDto(pet.getId(), pet.getNome(), pet.getTipo(), pet.getAdotado()))
                .collect(Collectors.toList());
    }
}
