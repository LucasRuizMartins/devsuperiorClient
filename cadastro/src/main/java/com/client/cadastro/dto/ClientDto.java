package com.client.cadastro.dto;

import com.client.cadastro.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDto {




    private Long id;

    private String name;


    private String cpf;
    private Double income;

    private LocalDate birthDate;
    private Integer children;


    public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDto(Client client) {
        id = client.getId();
        name = client.getName();
        cpf =  client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }

    public Long getId() {
        return id;
    }

    @NotBlank(message = "Campo requerido")
    public String getName() {
        return name;
    }

    public String getCpf() {return cpf;}

    public Double getIncome(){return income;}

    @PastOrPresent(message = "Nao é permitida data futura")
    public LocalDate getBirthDate(){return birthDate;}

    public Integer getChildren(){return children;}
}
