import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class DAO<T extends Identificable> implements Persistable<T> {
    private HashMap<Integer,T> map = new HashMap<>();

    @Override
    public T add(T t) {
        if (t!=null) {
            map.put(t.getId(),t);

            return t;
        } else {
            return null;
        }
    }
    @Override
    public T search(Integer id) {
        return (T)map.get(id);
    }
    @Override
    public T delete(Integer id) {
        return (T)map.remove(id);
         
    }

    @Override
    public HashMap<Integer, T> getMap() {
        return this.map;
    }
    
    public boolean existObj(Integer id){
        return map.containsKey(id);
    }

    public void save() throws IOException{
        System.out.println("---------------------");
        System.out.println("Guardando archivos...");
        System.out.println("---------------------\n");

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productes.dat"));
            oos.writeObject(this.map); 
            oos.close();
            
        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("Fallo al guardar el archivo");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX\n");
            e.printStackTrace();
        }
        System.out.println("------------------");
        System.out.println("Archivos guardados");
        System.out.println("------------------\n");
    }

    public void load() throws IOException{
        System.out.println("--------------------");
        System.out.println("Cargando archivos...");
        System.out.println("--------------------\n");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("productes.dat"));
            try {   
                this.map = (HashMap<Integer,T>)ois.readObject();
                ois.close();
            } catch (Exception e) {
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("Fallo al cargar el archivo");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX\n");

                e.printStackTrace();
            }
            System.out.println("-----------------");
            System.out.println("Archivos cargados");
            System.out.println("-----------------\n");

        } catch (Exception e) {
            System.out.println("XXXXXXXXXXXXXXXXXXXX");
            System.out.println("El archivo no existe");
            System.out.println("XXXXXXXXXXXXXXXXXXXX\n");
        }
    }
}