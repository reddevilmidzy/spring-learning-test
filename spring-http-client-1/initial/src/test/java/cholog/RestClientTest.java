package cholog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class RestClientTest {

    @Autowired
    private TodoClientWithRestClient todoClient;

    @Test
    void testGetTodos() {
        final List<Todo> todos = todoClient.getTodos();
        assertThat(todos).isNotEmpty();
    }

    @Test
    void testGetTodoWithId() {
        final Todo todo = todoClient.getTodoById(1L);
        assertThat(todo.getTitle()).isNotEmpty();
    }

    @Test
    void testGetTodoWithNonExistentId() {
        final Long nonExistentId = 9999L;

        assertThatThrownBy(() -> todoClient.getTodoById(nonExistentId))
                .isInstanceOf(TodoException.NotFound.class);
    }
}
