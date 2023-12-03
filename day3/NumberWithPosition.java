import java.util.Objects;

public class NumberWithPosition {
    String number;
    int start;
    int end;

    NumberWithPosition(String number, int start, int end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberWithPosition that = (NumberWithPosition) o;
        return start == that.start && end == that.end && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, number);
    }
}