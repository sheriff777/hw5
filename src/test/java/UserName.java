import lombok.Value;

@Value
public class UserName {
    private final String name;
    private final String surName;

    public String toString() {
        return this.getName() + " " + this.getSurName();
    }
}
