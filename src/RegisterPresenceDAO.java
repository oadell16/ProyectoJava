import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class RegisterPresenceDAO {
    private ArrayList<Presence> map = new ArrayList<Presence>();

    public Presence add(Presence obj) {
        if (obj!=null) {
            map.add(obj);
            return obj;
        }else{
            return null;
        }
        
    }
    
    public Presence delete(int index) {
        return map.remove(index);
    }

    public ArrayList<Presence> getMap() {
        return this.map;
    }

    public boolean existObj(Presence obj) {
        return this.map.contains(obj);
    }

    public int getIndex(Integer workerId, LocalDate date) {
        ArrayList<Presence> presenceTemp = searchMany(workerId);
        int index = 0;
        for (int i=0;i<presenceTemp.size();i++) {
            if (presenceTemp.get(i).getDate().compareTo(date)==0) {
                index = i;
            }
        }
        return index;
    }


    public Presence searchOne(Integer workerId, LocalDate date){
        return this.map.get(getIndex(workerId, date));
    }

    public ArrayList<Presence> searchMany(Integer workerId){
        ArrayList<Presence> presenceTemp = new ArrayList<Presence>();
        for (int i=0;i<this.map.size();i++) {
            if (this.map.get(i).getWorkerId()==workerId) {
                presenceTemp.add(this.map.get(i));
            }
        }
        return presenceTemp;
    }
    
}
