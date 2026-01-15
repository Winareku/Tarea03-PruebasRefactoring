package chainofresponsibility;

public abstract class IncidentHandler {
    protected IncidentHandler nextHandler;

    public IncidentHandler setNext(IncidentHandler handler) {
        this.nextHandler = handler;
        return handler;
    }

    public void handleIncident(Incident incident) {
        if (canHandle(incident)) {
            process(incident);
        } else if (nextHandler != null) {
            nextHandler.handleIncident(incident);
        }
    }

    public abstract boolean canHandle(Incident incident);

    public abstract void process(Incident incident);
}