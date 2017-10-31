import lyx.demo.hashmap.HashMap;
import lyx.demo.hashmap.Map;

/**
 * Created by landyChris on 2017/10/29.
 */
public class TestMap {

    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        long start = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            map.put("landy"+i,30);
        }
        for(int i=0;i<1000;i++){
            map.get("landy"+i);
        }
        long end = System.currentTimeMillis();
        System.out.println("size="+map.size()+",times="+(end-start));
    }

}
