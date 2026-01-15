package chainofresponsibility;

public class Incident {
    private String id;
    private String description;
    private int severity;
    private boolean resolved;

    public Incident(String id, String description, int severity) {
        this.id = id;
        this.description = description;
        this.severity = severity;
        this.resolved = false;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getSeverity() {
        return severity;
    }

    public boolean isLowSeverity() {
        return severity <= 2;
    }

    public boolean isMediumSeverity() {
        return severity > 2 && severity <= 5;
    }

    public boolean isHighSeverity() {
        return severity > 5;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void markResolved() {
        this.resolved = true;
    }
}
