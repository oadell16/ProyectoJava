import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ProviderDAO implements Persistable<Provider> {

    private HashMap<Integer,Provider> map = new HashMap<>();

    @Override
    public Provider add(Provider p) {
        if (p!=null) {
            map.put(p.getId(),p);
            return p;
        } else {
            return null;
        }
    }

    @Override
    public Provider search(Integer id) {
        return map.get(id);
    }

    @Override
    public Provider delete(Integer id) {
        return map.remove(id);
    }

    

    @Override
    public HashMap<Integer, Provider> getMap() {
        return this.map;
    }
    
    public boolean existObj(Integer id){
        return map.containsKey(id);
    }
}