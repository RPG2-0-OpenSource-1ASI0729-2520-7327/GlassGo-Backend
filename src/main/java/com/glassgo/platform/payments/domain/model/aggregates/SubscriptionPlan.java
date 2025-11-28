package com.glassgo.platform.payments.domain.model.aggregates;

import com.glassgo.platform.payments.domain.model.commands.CreateSubscriptionPlanCommand;
import com.glassgo.platform.payments.domain.model.commands.UpdateSubscriptionPlanCommand;
import com.glassgo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

/**
 * Aggregate that models a Subscription Plan.
 */
@Entity
@Getter
public class SubscriptionPlan extends AuditableAbstractAggregateRoot<SubscriptionPlan> {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration_months")
    private Integer durationMonths;

    public SubscriptionPlan() {
        this.name = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.price = null;
        this.durationMonths = null;
    }

    public SubscriptionPlan(CreateSubscriptionPlanCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
        this.durationMonths = command.durationMonths();
    }

    public SubscriptionPlan updateInformation(UpdateSubscriptionPlanCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
        this.durationMonths = command.durationMonths();
        return this;
    }
}
