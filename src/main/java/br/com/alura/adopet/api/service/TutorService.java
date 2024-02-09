package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.controller.TutorController;
import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private TutorController tutorController;

    public void cadastrar(TutorDto tutorDto) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(tutorDto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(tutorDto.email());

        if (telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        } else {
            Tutor tutor = new Tutor();
            tutor.setTelefone(tutorDto.telefone());
            tutor.setEmail(tutorDto.email());
            repository.save(tutor);
        }
    }
        public void atualizar (TutorDto tutorDto){
            Tutor tutor = repository.findById(tutorDto.id()).orElseThrow(() -> new ValidacaoException("Tutor não encontrado!"));
            tutor.setTelefone(tutorDto.telefone());
            tutor.setEmail(tutorDto.email());
            repository.save(tutor);
        }
    }

