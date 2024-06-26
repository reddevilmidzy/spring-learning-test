package cholog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class RestTemplateTest {

    @Autowired
    private TodoClientWithRestTemplate todoClient;

    @Test
    void testGetTodoWithId() {
        Todo todo = todoClient.getTodoById(1L);
        assertThat(todo.getTitle()).isNotEmpty();
    }

    @Test
    void testGetTodoWithNonExistentId() {
        Long nonExistentId = 9999L;

        assertThatThrownBy(() -> todoClient.getTodoById(nonExistentId))
                .isInstanceOf(TodoException.NotFound.class);
    }
}
