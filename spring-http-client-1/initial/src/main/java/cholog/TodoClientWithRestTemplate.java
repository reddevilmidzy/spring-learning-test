package cholog;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TodoClientWithRestTemplate {
    private final RestTemplate restTemplate;

    public TodoClientWithRestTemplate(RestTemplateBuilder builder) {
        this.restTemplate = builder.errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public Todo getTodoById(Long id) {
        // TODO: 존재하지 않는 id로 요청을 보낼 경우 TodoException.NotFound 예외를 던짐

        final ResponseEntity<Todo> entity = restTemplate.getForEntity("http://jsonplaceholder.typicode.com/todos/" + id,
                Todo.class);

        return entity.getBody();
    }
}
