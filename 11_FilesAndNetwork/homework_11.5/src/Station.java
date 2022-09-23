import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Station implements Comparable {

    private final Line line;
    private final String name;
    private Date date;
    private Double depth;
    private boolean hasConnection;

    public Station(String name, Line line) {
        this.name = name;
        this.line = line;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }

    public Line getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getDepth() {
        return depth;
    }

    @Override
    public int compareTo(Object o) {
        Station station = (Station) o;
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(line.getNumber());
        if (matcher.find()) {
            return 0;
        }
        return Integer.compare(Integer.parseInt(line.getNumber()), Integer.parseInt(station.getLine().getNumber()));
    }

}
