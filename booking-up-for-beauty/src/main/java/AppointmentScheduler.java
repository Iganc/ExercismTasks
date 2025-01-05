import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Month;
import java.util.Locale;

class AppointmentScheduler {
    public LocalDateTime schedule(String appointmentDateDescription) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm:ss");
        return LocalDateTime.parse(appointmentDateDescription, parser);

    }

    public boolean hasPassed(LocalDateTime appointmentDate) {
        LocalDateTime date1 = LocalDateTime.from(appointmentDate);
        LocalDateTime date2 = LocalDateTime.from(LocalDateTime.now());
        return date2.isAfter(date1);
    }

    public boolean isAfternoonAppointment(LocalDateTime appointmentDate) {
        return appointmentDate.getHour() >= 12 && appointmentDate.getHour() < 18;
    }

    public String getDescription(LocalDateTime appointmentDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy, 'at' h:mm a", Locale.US);
        return "You have an appointment on " + appointmentDate.format(formatter) + ".";
    }

    public LocalDate getAnniversaryDate() {
        return LocalDate.now().withMonth(Month.SEPTEMBER.getValue()).withDayOfMonth(15);
    }
}
