package cholog;

public class Todo {

    // TODO: Todo 객체가 가지는 필드들을 정의

    private final Long userId;
    private final Long id;
    private final String title;
    private final Boolean completed;

    protected Todo() {
        this.userId = null;
        this.id = null;
        this.title = null;
        this.completed = null;
    }

    public Todo(final Long userId, final Long id, final String title, final boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }
}
