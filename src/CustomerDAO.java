import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerDAO implements Persistable<Customer> {

    private HashMap<Integer,Customer> map = new HashMap<>();

    @Override
    public Customer add(Customer c) {
        if (c!=null) {
            map.put(c.getId(),c);
            return c;
        } else {
            return null;
        }
    }

    @Override
    public Customer search(Integer id) {
        return map.get(id);
    }

    @Override
    public Customer delete(Integer id) {
        return map.remove(id);
    }

    

    @Override
    public HashMap<Integer, Customer> getMap() {
        return this.map;
    }
    
    public boolean existObj(Integer id){
        return map.containsKey(id);
    }
}