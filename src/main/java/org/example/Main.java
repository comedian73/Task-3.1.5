package org.example;

import lombok.SneakyThrows;
import org.example.model.User;
import org.example.service.SiteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;


//@Configuration
@SpringBootApplication
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        SiteService siteService = ctx.getBean(SiteService.class);

        siteService.getUserList();

        siteService.addUser(new User(3L, "James", "Brown", (byte) 25));

        siteService.changeUser(new User(3L, "Thomas", "Shelby", (byte) 25));

        siteService.deleteUser(3L);

    }
}