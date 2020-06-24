package cat.pcolletm.events.application.port.out;

public interface LeaveEventPort {
    void leaveEvent(Long eventid, Long userId);
}
