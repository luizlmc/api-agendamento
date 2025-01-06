package br.com.luizlmc.agenda.domain.service;

import br.com.luizlmc.agenda.domain.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Client save(Client client);
    Client update(Long id, Client client);
    Page<Client> findAll(Pageable pageable);
    void delete(Long id);
    Client findByIdOrThrow(Long id);

}
