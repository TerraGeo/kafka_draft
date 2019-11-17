package kafka.enums;

public enum Topic {
    DEMO("test_topic");

    private String name;

    Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
