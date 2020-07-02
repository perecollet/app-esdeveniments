package cat.pcolletm.events.application.port.out;

public interface FillPdfFormUseCase {
    void fillPdf(Long eventId, Long userId);
}
