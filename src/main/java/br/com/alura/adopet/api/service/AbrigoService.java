package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;
    public List<Abrigo> listar() {
        return repository.findAll();
    }

    public List<Pet> listarPets(String idOuNome) {
        Abrigo abrigo = repository.findByIdOrNome(idOuNome);
        if (abrigo == null) {
            throw new ValidacaoException("Abrigo não encontrado");
        }
        return abrigo.getPets();
    }

    public void cadastrarPets(CadastrarPetDto dto) {
        if (dto == null || dto.abrigoId() == null || dto.nome() == null || dto.tipo() == null || dto.adotado() == null) {
            throw new IllegalArgumentException("DTO ou campos do DTO não podem ser nulos");
        }
        Abrigo abrigo = repository.findById(dto.abrigoId())
                .orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));
        Pet pet = new Pet();
        pet.setNome(dto.nome());
        pet.setTipo(dto.tipo());
        pet.setAdotado(dto.adotado());
        pet.setAbrigo(abrigo);
        abrigo.getPets().add(pet);
        repository.save(abrigo);
    }
}

