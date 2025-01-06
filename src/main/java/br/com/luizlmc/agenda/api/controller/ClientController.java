package br.com.luizlmc.agenda.api.controller;

import br.com.luizlmc.agenda.api.mapper.Impl.ClientMapperImpl;
import br.com.luizlmc.agenda.api.request.ClientRequest;
import br.com.luizlmc.agenda.api.response.ClientResponse;
import br.com.luizlmc.agenda.domain.service.Impl.ClientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientServiceImpl clientService;
    private final ClientMapperImpl clientMapperImpl;

    @PostMapping
    public ResponseEntity<ClientResponse> newClient(@RequestBody @Valid ClientRequest request) {
        var clientEntity = clientMapperImpl.toClientEntity(request);
        var savedClient = clientService.save(clientEntity);
        var clientResponse = clientMapperImpl.toClientResponse(savedClient);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedClient.getId())
                .toUri();

        return ResponseEntity.created(location).body(clientResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getOneClient(@PathVariable Long id) {
        var clientEntity = clientService.findByIdOrThrow(id);
        var clientResponse = clientMapperImpl.toClientResponse(clientEntity);

        return ResponseEntity.ok(clientResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponse>> getClients(Pageable pageable) {
        var clientsPage = clientService.findAll(pageable);
        var clientResponsePage = clientMapperImpl.toClientResponsePage(clientsPage);

        return ResponseEntity.ok(clientResponsePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Long id,
                                                       @RequestBody @Valid ClientRequest clientRequest) {

        var clientEntity = clientMapperImpl.toClientEntity(clientRequest);
        var updatedClient = clientService.update(id, clientEntity);
        var clientResponse = clientMapperImpl.toClientResponse(updatedClient);

        return ResponseEntity.ok(clientResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {

        clientService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
