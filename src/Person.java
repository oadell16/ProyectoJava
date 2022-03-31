import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Person implements Identificable{
    private static int numPersons;

    private int idPerson;
    private String dni;
    private String name;
    private String lastnames;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private Address address;

    
    public Person(int idPerson, String dni, String name, String lastnames) {
        this.idPerson = idPerson;
        this.dni = dni;
        this.name = name;
        this.lastnames = lastnames;
    }
    
    public boolean isValid(Person p){
        boolean result=true;

        if (p.getName().length()<1) {
            result= false;
        }

        if (p.getLastnames().length()<1) {
            result=false;
        }
        if (p.getDNI().length()!=9) {
            result =false;
        }
        return result;
    }

    public Person(int idPerson, String dni, String name, String lastnames, LocalDate birthDate, String email,
            String phone, Address address) {
        this.idPerson = idPerson;
        this.dni = dni;
        this.name = name;
        this.lastnames = lastnames;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getAge(){
        return (int)ChronoUnit.YEARS.between(birthDate,LocalDate.now());  
    }

    public static long diferenceAge (Person p1, Person p2){
        long diference = (int)ChronoUnit.YEARS.between(p1.birthDate,p2.birthDate);
        return diference;
    }
    
    public String toString() {
        return "Persona [Direccion=" + address + ", Apellidos=" + lastnames + ", Fecha de nacimiento=" + birthDate + ", DNI=" + dni + ", email=" + email + ", idPersona=" + idPerson + ", Nombre=" + name + ", Telefono=" + phone + ", Edat actual="+ getAge()+"]";
    }

    public static int getNumPersons() {
        return numPersons;
    }

    public int getId() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String DNI) {
        dni = DNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastnames() {
        return lastnames;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
