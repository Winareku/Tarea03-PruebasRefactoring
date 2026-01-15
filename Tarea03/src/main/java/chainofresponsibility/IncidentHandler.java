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

    protected abstract boolean canHandle(Incident incident);

    protected void process(Incident incident) {
        System.out.println(getHandlerName() + " manejando incidente: " + incident.getDescription());
        incident.markResolved();
    }

    protected abstract String getHandlerName();
}