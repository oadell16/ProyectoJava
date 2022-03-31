public class Address{
    private String population;
    private String province;
    private int pc;
    private String domicile;

    public Address(String population, String province, int pc, String domicile){
        this.population = population;
        this.province = province;
        this.pc = pc;
        this.domicile = domicile;
    }

    public String toString() {
        return "Direccion [cp=" + this.pc + ", domicilio=" + this.domicile + ", poblacion=" + this.population + ", provincia=" + this.province+ "]";
    }

    public void setPopulation(String population) {
        this.population = population;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public void setPc(int pc) {
        this.pc = pc;
    }
    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }
    public String getPopulation() {
        return population;
    }
    public String getProvince() {
        return province;
    }
    public int getPc() {
        return pc;
    }
    public String getDomicile() {
        return domicile;
    }
}
