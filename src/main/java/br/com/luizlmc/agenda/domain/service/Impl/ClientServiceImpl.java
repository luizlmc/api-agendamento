package br.com.luizlmc.agenda.domain.service.Impl;

import br.com.luizlmc.agenda.domain.entity.Client;
import br.com.luizlmc.agenda.domain.repository.ClientRepository;
import br.com.luizlmc.agenda.domain.service.ClientService;
import br.com.luizlmc.agenda.exception.ClientNotFoundException;
import br.com.luizlmc.agenda.exception.EmailAlreadyInUseException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        clientRepository.findByEmail(client.getEmail())
                .ifPresent(existingClient -> {
                    if (!existingClient.getId().equals(client.getId())) {
                        throw new EmailAlreadyInUseException("Email already in use: " + client.getEmail());
                    }
                });

        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client newClient) {
        var existingClient = this.findByIdOrThrow(id);

        existingClient.setName(newClient.getName());
        existingClient.setEmail(newClient.getEmail());
        existingClient.setPhone(newClient.getPhone());

        return clientRepository.save(existingClient);
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        this.findByIdOrThrow(id);

        clientRepository.deleteById(id);
    }

    @Override
    public Client findByIdOrThrow(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
    }
}
