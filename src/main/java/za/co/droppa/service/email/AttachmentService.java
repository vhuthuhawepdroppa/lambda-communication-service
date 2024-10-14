package za.co.droppa.service.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.droppa.dto.Pair;
import za.co.droppa.service.aws.S3Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AttachmentService {

    S3Service s3Service;

    public List<Pair<String, InputStream>> bookingCreationAttachments(String bookingTrackNo){
        List<Pair<String, InputStream>> attachments = new ArrayList<>();

        String waybillPath = "waybills/"+bookingTrackNo+".pdf";

        InputStream checklist = s3Service.loadFileFromS3("droppa-document","checklist.pdf");
        InputStream waybill = s3Service.loadFileFromS3("droppa-document",waybillPath);

        attachments.add(new Pair<>("Checklist", checklist));
        attachments.add(new Pair<>("WAYBILL_"+bookingTrackNo, waybill));

        return attachments;
    }
}
