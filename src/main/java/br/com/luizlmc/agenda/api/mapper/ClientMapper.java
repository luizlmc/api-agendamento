package br.com.luizlmc.agenda.api.mapper;

import br.com.luizlmc.agenda.api.request.ClientRequest;
import br.com.luizlmc.agenda.api.response.ClientResponse;
import br.com.luizlmc.agenda.domain.entity.Client;
import org.springframework.data.domain.Page;

public interface ClientMapper {

    Client toClientEntity(ClientRequest request);
    ClientResponse toClientResponse(Client client);
    Page<ClientResponse> toClientResponsePage(Page<Client> clients);
}
