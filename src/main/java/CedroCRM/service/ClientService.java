package CedroCRM.service;

import CedroCRM.infra.model.Client;
import CedroCRM.infra.model.dto.ClientDto;
import CedroCRM.infra.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private final ModelMapper modelMapper;

    final ClientRepository repository;

    public List<ClientDto> findAll() {
        return repository.findAll().stream().map(this::convertClient).collect(Collectors.toList());
    }

    public Optional<ClientDto> getById(Long id) {
        return repository.findById(id).map(this::convertClient);
    }

    public Client save(ClientDto clientDto) {
        return repository.save(modelMapper.map(clientDto, Client.class));
    }


    public ClientDto convertClient(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }


    public ClientDto update(Long id, ClientDto clientDto) {
        Assert.notNull(id, "Unable to update registration");
        Optional<Client> optional = repository.findById(id);
        if (optional.isPresent()) {
            Client db = optional.get();
            db.setName(clientDto.getName());
            db.setCpf(clientDto.getCpf());
            repository.save(db);
            return this.convertClient(db);
        }
        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
