package com.client.cadastro.repositories;


import com.client.cadastro.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {



}
