package module.dao.person;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    private Integer id;
    private String fio;
    private Date date;
    private final String pattern = "dd.MM.yyyy";
    private String gender;

    public Person() {
        id = 0;
        fio = "";
        date = null;
        gender = "";
    }

    public Person(Integer id, String fio, Date date, String gender) {
        this.id = id;
        this.fio = fio;
        this.date = date;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormattedDate() {
        return new SimpleDateFormat(pattern).format(date);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
