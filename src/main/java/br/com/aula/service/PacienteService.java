package br.com.aula.service;

import br.com.aula.model.Paciente;
import br.com.aula.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Paciente gravaPaciente(Paciente paciente){
        return repository.save(paciente);
    }

    public List<Paciente> buscarTudo(){
        return repository.findAll();
    }

    public Optional<Paciente> buscaId(Integer id_paciente){
        return repository.findById(id_paciente);
    }

    public void deletaPaciente(Optional<Paciente> paciente){
        repository.delete(paciente.get());
    }
}
