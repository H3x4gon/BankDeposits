package ru.emelv.BankDeposits.service;

import ru.emelv.BankDeposits.entity.Client;
import ru.emelv.BankDeposits.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients(Sort sort) {
        return clientRepository.findAll(sort);
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {
        return clientRepository.findById(id).map(client -> {
            client.setName(updatedClient.getName());
            client.setEmail(updatedClient.getEmail());
            return clientRepository.save(client);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}