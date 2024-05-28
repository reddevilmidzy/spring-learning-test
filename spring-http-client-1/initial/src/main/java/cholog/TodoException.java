package cholog;

public class TodoException extends RuntimeException {
    public TodoException(String message) {
        super(message);
    }

    public static class NotFound extends TodoException {
        public NotFound() {
            super("존재하지 않는 아이디임 ㅅㄱ");
        }

        public NotFound(Long id) {
            super("Todo not found with id: " + id);
        }
    }
}
