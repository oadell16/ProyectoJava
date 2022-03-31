import java.time.LocalDate;

public class Customer implements Identificable{
    private int idPerson;
    private String dni;
    private String name;
    private String lastnames;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private Address address;

    public Customer(int idPerson, String dni, String name, String lastnames, LocalDate birthDate, String email, String phone, Address address) {
        this.idPerson = idPerson;
        this.dni = dni;
        this.name = name;
        this.lastnames = lastnames;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    @Override
    public String toString() {
        return "Cliente [Direccion=" + address + ", Apellidos=" + lastnames + ", Fecha de nacimiento=" + birthDate + ", DNI=" + dni + ", email=" + email + ", idPersona=" + idPerson + ", Nombre=" + name + ", Telefono=" + phone + "]";
    }
    @Override
    public int getId() {
        return this.idPerson;
    }
    
}
