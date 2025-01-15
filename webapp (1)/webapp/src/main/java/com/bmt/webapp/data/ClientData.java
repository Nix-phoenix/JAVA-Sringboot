package com.bmt.webapp.data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.bmt.webapp.model.Client;

public class ClientData {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String status;
    private LocalDate createdAt;

    // Constructor
    public ClientData(int id, String firstName, String lastName, String email, String phone, String address, String status, LocalDate createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Static method to convert a single Client to ClientData
    public static ClientData fromClient(Client client) {
        return new ClientData(
            client.getId(),
            client.getFirstName(),
            client.getLastName(),
            client.getEmail(),
            client.getPhone(),
            client.getAddress(),
            client.getStatus(),
            client.getCreatedAt().toLocalDate() // Convert to LocalDate
        );
    }

    // Static method to convert a list of Clients to a list of ClientData
    public static List<ClientData> fromClients(List<Client> clients) {
        return clients.stream()
                .map(ClientData::fromClient)
                .collect(Collectors.toList());
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}