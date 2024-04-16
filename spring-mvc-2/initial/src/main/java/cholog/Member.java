package cholog;

public class Member {
    private Long id;
    private String name;
    private int age;

    public Member() {
    }

    public Member(final Long id, final String name, final int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Member(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static Member toEntity(final Member member, final Long id) {
        return new Member(id, member.name, member.age);
    }

    public void update(final Member newMember) {
        this.name = newMember.name;
        this.age = newMember.age;
    }
}
