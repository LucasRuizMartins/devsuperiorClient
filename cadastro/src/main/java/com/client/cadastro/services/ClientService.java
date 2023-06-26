package com.client.cadastro.services;

import com.client.cadastro.dto.ClientDto;
import com.client.cadastro.entities.Client;
import com.client.cadastro.repositories.ClientRepository;
import com.client.cadastro.services.exceptions.DatabaseException;
import com.client.cadastro.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {


    @Autowired
    private ClientRepository repository;

@Transactional(readOnly = true)
    public ClientDto findById(Long id) {
    Client client = repository.findById(id).orElseThrow(
            ()-> new ResourceNotFoundException("Recurso nao encontrado"));
    return new ClientDto(client);

}

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll (Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return  result.map(x -> new  ClientDto(x));
    }


    @Transactional()
    public ClientDto insert (ClientDto dto) {
        Client entity = new Client();
        copyDtoToEntity(dto,entity);
        entity = repository.save(entity);

        return new ClientDto(entity);
    }

    @Transactional()
    public ClientDto update (Long id, ClientDto dto) {
    try{
        Client entity =repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDto(entity);
} catch (EntityNotFoundException e) {
        throw new ResourceNotFoundException("Recurso nao encotrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
        catch (DataIntegrityViolationException e) {
           throw new DatabaseException("Erro em integridade Referencial");
        }
    }

    private void copyDtoToEntity(ClientDto dto, Client entity) {

            entity.setName(dto.getName());
            entity.setCpf(dto.getCpf());
            entity.setBirthDate(dto.getBirthDate());
            entity.setIncome(dto.getIncome());
            entity.setChildren(dto.getChildren());

        }

    }



