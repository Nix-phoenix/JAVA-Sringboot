package com.bmt.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmt.webapp.data.ClientData;
import com.bmt.webapp.model.Client;
import com.bmt.webapp.model.ClientDto;
import com.bmt.webapp.repositories.ClientRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientControllers {

    @Autowired
    private ClientRepository clientRepo;

    @GetMapping({"", "/"})
    public String getClients(Model model) {
        var clients = clientRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<ClientData> clientDataList = ClientData.fromClients(clients);

        model.addAttribute("clients", clientDataList);
        return "clients/index";
    }

    @GetMapping("/create")
    public String createClient(Model model) {
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        return "clients/create";
    }

    @PostMapping("/create")
    public String createClient(
        @Valid @ModelAttribute ClientDto clientDto,
        BindingResult result
    ) {
        if (clientRepo.findByEmail(clientDto.getEmail()) != null) {
            result.addError(new FieldError(
                "clientDto",
                "email",
                clientDto.getEmail(),
                false,
                null,
                null,
                "Email already exists"
            ));
        }

        if (result.hasErrors()) {
            return "clients/create";
        }

        Client client = new Client();
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setStatus(clientDto.getStatus());
        client.setCreatedAt(new java.sql.Date(System.currentTimeMillis())); // Set current date

        clientRepo.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit")
    public String editClient(Model model, @RequestParam int id) {
        Client client = clientRepo.findById(id).orElse(null);
        if (client == null) {
            return "redirect:/clients";
        }

        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setAddress(client.getAddress());
        clientDto.setStatus(client.getStatus());

        model.addAttribute("client", client);
        model.addAttribute("clientDto", clientDto);

        return "clients/edit"; // You can update the return view if needed
    }

    @PostMapping("/edit")
    public String editClient(
        @RequestParam int id,
        @Valid @ModelAttribute ClientDto clientDto,
        BindingResult result,
        Model model
    ) {
        Client client = clientRepo.findById(id).orElse(null);
        if (client == null) {
            return "redirect:/clients";
        }
    
        // If there are validation errors, return to the form with the existing data
        if (result.hasErrors()) {
            model.addAttribute("client", client);
            return "clients/edit"; // Return to the form with errors
        }
    
        // Check if the email is already taken by another client
        if (clientRepo.findByEmail(clientDto.getEmail()) != null && !clientDto.getEmail().equals(client.getEmail())) {
            result.addError(new FieldError(
                "clientDto", "email", clientDto.getEmail(),
                false, null, null, "Email already exists"
            ));
            model.addAttribute("client", client);  // Add existing client info to model
            return "clients/edit"; // Return to form with email error
        }
    
        // Update the client details
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setStatus(clientDto.getStatus());
    
        // Save the updated client to the database
        clientRepo.save(client);
    
        return "redirect:/clients"; // Redirect to clients list page after successful edit
    }

    @DeleteMapping("/delete")
public String deleteClient(Model model, @RequestParam int id) {
    Client client = clientRepo.findById(id).orElse(null);
    if (client != null) {
        clientRepo.delete(client);
    }
    return "redirect:/clients";
}

}
