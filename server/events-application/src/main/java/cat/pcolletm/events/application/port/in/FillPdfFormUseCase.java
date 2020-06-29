package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.domain.Event;

public interface FillPdfFormUseCase {
    void fillPdf(Long eventId, Long userId);
}
