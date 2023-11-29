package br.com.aula.controller;

import br.com.aula.model.Paciente;
import br.com.aula.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Paciente> gravarPaciente(@RequestBody Paciente paciente) {
        Paciente novoPaciente = service.gravaPaciente(paciente);
        return ResponseEntity.status(HttpStatus.OK).body(novoPaciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTudo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarID(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscaId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaPaciente(@PathVariable(value = "id") Integer id, @RequestBody Paciente paciente) {

        Optional<Paciente> p = service.buscaId(id);

        if(p.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Paciente não encontrado!");
        }

        Paciente paciente1 = p.get();
        paciente1.setNm_paciente(paciente.getNm_paciente());
        paciente1.setPeso(paciente.getPeso());
        paciente1.setAltura(paciente.getAltura());

        return ResponseEntity.status(HttpStatus.OK).body(service.gravaPaciente(paciente1));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPaciente(@PathVariable(value = "id") Integer id) {

        Optional<Paciente> p = service.buscaId(id);

        if(p.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Paciente não encontrado!");
        }

        service.deletaPaciente(p);

        return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado!");

    }
}
