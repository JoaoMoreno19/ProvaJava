package br.com.aula.controller;

import br.com.aula.model.Paciente;
import br.com.aula.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/IMC")
public class ImcController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{idPaciente}")
    public ResponseEntity<Object> gerarDados(@PathVariable(value = "idPaciente") Integer idPaciente) {
        Optional<Paciente> pacienteOptional = pacienteService.buscaId(idPaciente);

        if (pacienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado!");
        }

        Paciente paciente = pacienteOptional.get();
        Double Imc = paciente.getPeso() / (paciente.getAltura() * paciente.getAltura());
        String status = "";

        if (Imc <= 18.5) {
            status = "Está a baixo do peso";
        } else if (Imc > 18.5 && Imc < 25) {
            status = "Peso consierado normal";
        } else if (Imc >= 25 && Imc < 30) {
            status = "Existe um possível sobrepeso";
        }else if (Imc >= 30 && Imc < 35) {
            status = "Trata-se de obesidade grau 1";
        }else if (Imc >= 35 && Imc < 40) {
            status = "Obesidade grau 2";
        }else {
            status = "Obesidade grau 3, também chamada de obesidade mórbida, justamente pelo alto risco de comorbidades.";
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("Nome do paciente", paciente.getNm_paciente());
        resultado.put("Peso", paciente.getPeso());
        resultado.put("Altura", paciente.getAltura());
        resultado.put("IMC", Imc);
        resultado.put("Status", status);

        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}
