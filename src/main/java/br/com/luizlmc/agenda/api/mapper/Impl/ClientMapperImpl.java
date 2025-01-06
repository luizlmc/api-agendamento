package br.com.luizlmc.agenda.api.mapper.Impl;

import br.com.luizlmc.agenda.api.mapper.ClientMapper;
import br.com.luizlmc.agenda.api.request.ClientRequest;
import br.com.luizlmc.agenda.api.response.ClientResponse;
import br.com.luizlmc.agenda.domain.entity.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapperImpl implements ClientMapper {

    private final ModelMapper mapper;

    @Override
    public Client toClientEntity(ClientRequest request) {
        return mapper.map(request, Client.class);
    }

    @Override
    public ClientResponse toClientResponse(Client client) {
        return new ClientResponse(client.getName(), client.getEmail(), client.getPhone());
    }

    @Override
    public Page<ClientResponse> toClientResponsePage(Page<Client> clients) {
        return clients.map(this::toClientResponse);
    }

}
