package com.savage.kameleoon.controller;


import com.savage.kameleoon.dto.ClientDTO;
import com.savage.kameleoon.models.Client;
import com.savage.kameleoon.services.ClientService;
import com.savage.kameleoon.util.client.ClientErrorResponse;
import com.savage.kameleoon.util.client.ClientNotCreatedException;
import com.savage.kameleoon.util.client.ClientNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
public class ClientController {

    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getClients() {
        return clientService.findAll().stream().map(this::convertClientDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable("id") Long id) {
        return convertClientDTO(clientService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ClientDTO clientDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMassage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMassage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new ClientNotCreatedException(errorMassage.toString());
        }
        clientService.save(convertToClient(clientDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException() {
        ClientErrorResponse response = new ClientErrorResponse("Person with this id wasn't found!");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotCreatedException exception) {
        ClientErrorResponse response = new ClientErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Client convertToClient(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    private ClientDTO convertClientDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }
}
