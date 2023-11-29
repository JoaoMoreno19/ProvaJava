package br.com.aula.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PACIENTE")
public class Paciente implements Serializable {
    @Id
    @Column(name = "ID_PACIENTE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_paciente;

    @Column(name = "NM_PACIENTE")
    private String nm_paciente;

    @Column(name = "PESO")
    private Double peso;

    @Column(name = "ALTURA")
    private Double altura;

    public Paciente() {
    }

    public Integer getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Integer id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNm_paciente() {
        return nm_paciente;
    }

    public void setNm_paciente(String nm_paciente) {
        this.nm_paciente = nm_paciente;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }
}
