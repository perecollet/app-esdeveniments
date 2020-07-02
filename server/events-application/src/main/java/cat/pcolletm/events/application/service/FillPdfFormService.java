package cat.pcolletm.events.application.service;

import cat.pcolletm.events.application.port.out.FillPdfFormUseCase;
import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.in.LoadUsersPort;
import cat.pcolletm.events.common.UseCase;
import cat.pcolletm.events.domain.Event;
import cat.pcolletm.events.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import javax.transaction.Transactional;
import java.io.File;
import java.text.SimpleDateFormat;

@RequiredArgsConstructor
@UseCase
@Transactional
public class FillPdfFormService implements FillPdfFormUseCase {

    private final LoadEventsPort loadEventsPort;
    private final LoadUsersPort loadUsersPort;

    @Override
    public void fillPdf(Long eventId, Long userId) {

        Event event = loadEventsPort.loadEventById(eventId);
        User user = loadUsersPort.loadUserById(userId);
        try {
            PDDocument pdDocument = PDDocument.load(new File("../Formularis esdeveniments/memo.pdf"));
            PDAcroForm pdAcroForm = pdDocument.getDocumentCatalog().getAcroForm();

            PDField field = pdAcroForm.getField("Campo de texto 9");
            field.setValue(user.getName() + " " + user.getSurname());
            field = pdAcroForm.getField("Campo de texto 10");
            field.setValue(user.getDni());
            field = pdAcroForm.getField("Campo de texto 11");
            field.setValue(user.getPhone());
            field = pdAcroForm.getField("Campo de texto 13");
            field.setValue(user.getEmail());

            field = pdAcroForm.getField("Campo de texto 19");
            field.setValue(event.getActivity());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            field = pdAcroForm.getField("Campo de texto 20");
            field.setValue(sdf.format(event.getStartTime()));
            sdf = new SimpleDateFormat("HH:mm");
            field = pdAcroForm.getField("Campo de texto 21");
            field.setValue(sdf.format(event.getStartTime()));
            field = pdAcroForm.getField("Campo de texto 22");
            field.setValue(sdf.format(event.getEndTime()));
            field = pdAcroForm.getField("Campo de texto 23");
            field.setValue(event.getLocation());
            field = pdAcroForm.getField("Campo de texto 25");
            field.setValue("No");
            field = pdAcroForm.getField("Campo de texto 26");
            field.setValue(String.valueOf(event.getNumParticipants()));
            field = pdAcroForm.getField("Campo de texto 27");
            field.setValue("No");
            field = pdAcroForm.getField("Campo de texto 29");
            field.setValue(event.getDescription());

            pdDocument.save("../Formularis esdeveniments/Formularis omplerts/formular_" + eventId + ".pdf");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
