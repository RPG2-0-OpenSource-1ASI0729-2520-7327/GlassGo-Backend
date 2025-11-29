package com.glassgo.platform.clients.domain.model.aggregates;

import com.glassgo.platform.clients.domain.model.commands.CreateClientCommand;
import com.glassgo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Client extends AuditableAbstractAggregateRoot<Client> {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "subscription_id", nullable = false)
    private Long subscriptionId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    public Client() {
        super();
    }

    public Client(CreateClientCommand command) {
        this.userId = command.userId();
        this.subscriptionId = command.subscriptionId();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.email = command.email();
        this.phone = command.phone();
    }
}
