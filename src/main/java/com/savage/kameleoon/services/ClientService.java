package com.savage.kameleoon.services;


import com.savage.kameleoon.models.Client;
import com.savage.kameleoon.repository.ClientRepository;
import com.savage.kameleoon.util.client.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findOne(Long id) {
        Optional<Client> foundClient = clientRepository.findById(id);
        return foundClient.orElseThrow(ClientNotFoundException::new);
    }


    @Transactional
    public void save(Client client) {
        upgradeClient(client);
        clientRepository.save(client);
    }

    private void upgradeClient(Client client) {
        client.setRegistrationDate(LocalDateTime.now());
    }

}
