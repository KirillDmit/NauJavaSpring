package ru.dmitriev.NauJavaSpring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EventCustomRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testFindByEventTimeBetweenAndTitle_CriteriaAPI() {
        // similar to previous test but calls Criteria API method
    }
}
