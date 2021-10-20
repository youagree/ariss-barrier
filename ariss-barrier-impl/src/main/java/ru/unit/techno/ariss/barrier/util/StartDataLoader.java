package ru.unit.techno.ariss.barrier.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import ru.unit.techno.ariss.log.action.lib.repository.EventRepository;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartDataLoader implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final EventRepository repository;

    @Override
    public void run(String... args) throws Exception {
        /// TODO: 20.10.2021 Вынести в отдельный профиль!
        repository.deleteAll();

        Resource resource = new ClassPathResource("test-data-events.sql");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource);
        populator.setSqlScriptEncoding("UTF-8");
        populator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));

        log.info("Start data uploaded!");
    }
}