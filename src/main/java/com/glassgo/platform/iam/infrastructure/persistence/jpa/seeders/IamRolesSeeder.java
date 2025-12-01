package com.glassgo.platform.iam.infrastructure.persistence.jpa.seeders;

import com.glassgo.platform.iam.domain.model.commands.SeedRolesCommand;
import com.glassgo.platform.iam.domain.model.services.RoleCommandService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class IamRolesSeeder implements ApplicationRunner {
    private final RoleCommandService roleCommandService;

    public IamRolesSeeder(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleCommandService.handle(new SeedRolesCommand());
    }
}
