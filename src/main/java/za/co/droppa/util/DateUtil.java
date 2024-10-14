package za.co.droppa.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class DateUtil {

    private static final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
    public static String format(LocalTime value) {
        Objects.requireNonNull(value);
        return value.format(tf);
    }

    public static String getTodayForEmailTemplates(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

}
