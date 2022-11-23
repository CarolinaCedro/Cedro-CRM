package CedroCRM.api;

import CedroCRM.infra.model.Client;
import CedroCRM.infra.model.dto.ClientDto;
import CedroCRM.service.ClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ClientController {

    private final ClientService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<ClientDto> client = service.getById(id);
        return client.isPresent() ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody @Valid ClientDto clientDto) {
        Client client = new Client(clientDto.getName(), clientDto.getCpf(),clientDto.getRegistrationTime());
        return new ResponseEntity(service.save(modelMapper.map(client, ClientDto.class)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ClientDto clientDto) {
        clientDto.setId(id);
        ClientDto client = service.update(id, clientDto);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<ClientDto> client = service.getById(id);
        if (client.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
