package com.glassgo.platform.payments.domain.model.aggregates;

import com.glassgo.platform.payments.domain.model.commands.CreateSubscriptionCommand;
import com.glassgo.platform.payments.domain.model.commands.UpdateSubscriptionCommand;
import com.glassgo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

/**
 * Aggregate representing a user's Subscription.
 * <p>
 * Encapsulates subscription state and provides constructors and behavior used
 * by the application services to create and update subscriptions.
 */
@Entity
@Getter
public class Subscription extends AuditableAbstractAggregateRoot<Subscription> {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "status")
    private String status;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "auto_renew")
    private Boolean autoRenew;

    public Subscription() {
        this.userId = null;
        this.planId = null;
        this.status = Strings.EMPTY;
        this.startDate = null;
        this.endDate = null;
        this.autoRenew = false;
    }

    public Subscription(CreateSubscriptionCommand command) {
        this.userId = command.userId();
        this.planId = command.planId();
        this.status = command.status();
        this.startDate = command.startDate();
        this.endDate = command.endDate();
        this.autoRenew = command.autoRenew();
    }

    public Subscription updateInformation(UpdateSubscriptionCommand command) {
        this.planId = command.planId();
        this.status = command.status();
        this.autoRenew = command.autoRenew();
        return this;
    }
}
