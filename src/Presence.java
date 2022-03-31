import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Presence implements Comparable <Presence>{
    private Integer workerId;
    private LocalDate date;
    private LocalTime entryHour;
    private LocalTime leavingHour;

    public Presence(Integer workerId,LocalDate date, LocalTime entryHour, LocalTime leavingHour) {
       this.workerId = workerId;
       this.date = date;
       this.entryHour = entryHour;
       this.leavingHour = leavingHour;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEntryHour() {
        return entryHour;
    }

    public void setEntryHour(LocalTime entryHour) {
        this.entryHour = entryHour;
    }

    public LocalTime getLeavingHour() {
        return leavingHour;
    }

    public void setLeavingHour(LocalTime leavingHour) {
        this.leavingHour = leavingHour;
    }
    

    @Override
    public int compareTo(Presence obj) {
        return this.workerId.compareTo(obj.workerId);
    }

    @Override
    public String toString() {
        return "Presence [date=" + date + ", entryHour=" + entryHour + ", leavingHour=" + leavingHour + ", workerId="
                + workerId + "]";
    }
    
    
}
