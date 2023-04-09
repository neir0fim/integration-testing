package com.integration.persistence.exercise;

import com.integration.models.exercise.Exercise;
import com.integration.models.exercise.ExerciseUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = {ExerciseAdapter.class, FlywayConfig.class})
class ExerciseAdapterTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private ExerciseAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ExerciseAdapter(namedParameterJdbcTemplate);
    }

    @Test
    void shouldReturnListOfExercise() {
        assertEquals(
                new Exercise(1, "bench press", "chest"),
                adapter.getExercise(1)
        );
    }

    @Test
    void shouldDeleteExerciseFromDb() {
        adapter.deleteExercise(2);

        assertThrows(EmptyResultDataAccessException.class,
                () -> adapter.getExercise(2)
        );
    }

    @Test
    void shouldUpdateExerciseFromDb() {
        adapter.updateExercise(2, new ExerciseUpdateRequest("Updated pull up", 2));

        assertEquals(
                new Exercise(2, "Updated pull up", "back"),
                adapter.getExercise(2)
        );
    }

    @Test
    void shouldAddNewExerciseFromDb() {
        adapter.addExercise(new ExerciseUpdateRequest("squad", 5));

        assertEquals(
                new Exercise(3, "squad", "hamstrings"),
                adapter.getExercise(3)
        );
    }

    @Test
    void shouldAllExerciseFromDb() {
        assertEquals(
                List.of(
                        new Exercise(1, "bench press", "chest"),
                        new Exercise(2,  "pull up", "back")
                ),
                adapter.getALLExercise()
        );
    }
}