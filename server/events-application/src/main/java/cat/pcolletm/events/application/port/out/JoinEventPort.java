package cat.pcolletm.events.application.port.out;

public interface JoinEventPort {

    void joinEvent (Long eventId, Long userId);

}
