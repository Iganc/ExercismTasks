import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {
    LocalDateTime modifiedDate;
    public Gigasecond(LocalDate moment) {
        modifiedDate = moment.atStartOfDay().plusYears(31).plusDays(251).plusHours(1).plusMinutes(46).plusSeconds(40);
    }

    public Gigasecond(LocalDateTime moment) {
        modifiedDate = moment.plusYears(31).plusDays(251).plusHours(1).plusMinutes(46).plusSeconds(40);
    }

    public LocalDateTime getDateTime() {
        return modifiedDate;
    }
}
