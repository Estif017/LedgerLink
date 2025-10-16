package pluralsight;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction( LocalDate date,LocalTime time,String description, String vendor, double amount) {
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.time = time;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static String header() {
        String header = String.format("%-12s | %-25s | %-20s | %10s",
                "Date", "Description", "Vendor", "Amount");
        String separator = "-".repeat(12 + 25 + 20 + 10 + 9); // column widths + separators
        return header + "\n" + separator;
    }

    @Override
    public String toString() {
        return String.format( "%-12s | %-25s | %-20s | %10.2f",
                date,
                description,
                vendor,
                amount);
    }
}


