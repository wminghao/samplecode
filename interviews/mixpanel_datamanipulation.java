import java.io.*;
import java.util.*;
//import org.json.*;

/*
 * This is a common problem,
 * 1. in E-commerce websites, search can return lots of results. Add many filters to refine the results.
 * 2. in P-interest style websites, main page can return lots of results, add many filters to refine the interested boards and pins
 * ------>K-D trees.
 * Improved algorithm, for each keyword, build a hashtable where each node is a balanced binary tree(Sorted by item_id).
 *                     
 * Where there is ONE filter, look up the hashtable and find the tree in that hashtable and return.
 * Where there is TWO filters, look up 2 hashtables and find 2 trees in that hashtable and return.
 *                             From the small tree, look up the entries from the big tree.
 * Where there is N filters, look up n hashtables and find n trees in that hashtable and return.
 *                             From the smallest tree, look up all the entries from the biggest tree.
 *                             From the result, look up all the entries from the NEXT biggest tree.
 *                             Until all trees are traversed.
 * The result is sorted by item_id, we can also sort the result based on time, popularities, promotion, scores, etc.
 */

class Solution {
  
    private static class Properties{
	public String city;
	public String gender;
	public int item_id;
	public String hair;
	public int value;
    
	public Properties(String c, String g, int i, String h, int v) {
	    this.city = c;
	    this.gender = g;
	    this.item_id = i;
	    this.hair = h;
	    this.value = v;
	}
    }
  
  
    public static HashMap<String, ArrayList<Properties>> ht = new HashMap<String, ArrayList<Properties>>();
  
    public static void main(String[] args) {
    
    /*
    String jsonData = "some data";
      
    JSONObject jObject = new JSONObject( jsonData);
    Iterator<?> keys = jObject.keys();
    while( keys.hasNext() ) {
      String key = (String)keys.next();
      Object obj = jObject.get(key);
      if ( obj  instanceof JSONObject ) {
          jSubObj = (JSONObject)obj;
          String hair = jSubObj.get("hair");
          String city = jSubObj.get("city");
          int item_id = jSubObj.get("item_id");
          int value = jSubObj.get("value");
          String gender = jSubObj.get("gender");
          
          Properties prop = new Properties(city, gender, item_id, hair, value);
      }
    }
	*/
    
	Properties prop1 = new Properties("NYC", "M", 876, "brown", 23);
	Properties prop2 = new Properties("NYC", "F", 123, "blue", 3);
	ArrayList<Properties> list1 = new ArrayList<Properties>();
	list1.add(prop1);
	list1.add(prop2);
	ht.put("Purchase", list1);
    
	ArrayList<Properties> list2 = new ArrayList<Properties>();
	Properties prop3 = new Properties("NYC", "M", 223, "black", 123);
	list2.add(prop3);
	ht.put("Signup", list2);
    
	String event = "Signup";
	HashSet<Properties> res = multiseg(event, "NYC", "M", "black");
	for(Properties p: res) {
	    System.out.println("Event="+event+" hair="+p.hair+" gender"+p.gender+" city="+p.city);
	}
    }
  
    private static HashSet<Properties> multiseg(String event, 
						String city, 
						String gender, 
						String hair) 
    {
	HashSet<Properties> res = new HashSet<Properties>();
	ArrayList<Properties> list = ht.get(event);
	System.out.println("list size="+list.size());
	for (int i = 0; i < list.size(); i++) {
	    Properties p = list.get(i);
	    if(p.gender == gender &&
           p.city == city &&
	       p.hair == hair) {
		res.add( p );
	    }
	}
	return res;
    }
}


/* 
Your previous Plain Text content is preserved below:

JSONdata = [{"event": "Purchase", "properties": {"hair": "brown", "city": "NYC", "item_id": 876, "value": 23, "gender": "M"}}, {"event": "Signup", "properties": {"hair": "green", "city": "NYC", "origin": "twitter", "gender": "M"}}, {"event": "Purchase", "properties": {"hair": "blue", "city": "SF", "item_id": 876, "value": 20, "gender": "M"}}, {"event": "Purchase", "properties": {"hair": "red", "city": "SF", "item_id": 123, "value": 55, "gender": "F"}}, {"event": "Signup", "properties": {"hair": "brown", "city": "NYC", "origin": "google", "gender": "F"}}, {"event": "Signup", "properties": {"hair": "purple", "city": "NYC", "origin": "twitter", "gender": "F"}}, {"event": "Signup", "properties": {"hair": "brown", "city": "Oakland", "origin": "google", "gender": "M"}}, {"event": "Signup", "properties": {"hair": "blond", "city": "NYC", "origin": "google", "gender": "F"}}, {"event": "Purchase", "properties": {"hair": "red", "city": "Oakland", "item_id": 123, "value": 55, "gender": "M"}}]

# existing mixpanel events
data = [
    { 'event': 'Purchase', 'properties': { 'city': 'NYC', 'gender': 'M', 'hair': 'brown', 'value': 23, 'item_id': 876 } },
    { 'event': 'Signup', 'properties': { 'city': 'NYC', 'gender': 'M', 'hair': 'green', 'origin': 'twitter' } },
    { 'event': 'Purchase', 'properties': { 'city': 'SF', 'gender': 'M', 'hair': 'blue', 'value': 20, 'item_id': 876 } },
    { 'event': 'Purchase', 'properties': { 'city': 'SF', 'gender': 'F', 'hair': 'red', 'value': 55, 'item_id': 123 } },
    { 'event': 'Signup', 'properties': { 'city': 'NYC', 'gender': 'F', 'hair': 'brown', 'origin': 'google' } },
    { 'event': 'Signup', 'properties': { 'city': 'NYC', 'gender': 'F', 'hair': 'purple', 'origin': 'twitter' } },
    { 'event': 'Signup', 'properties': { 'city': 'Oakland', 'gender': 'M', 'hair': 'brown', 'origin': 'google' } },
    { 'event': 'Signup', 'properties': { 'city': 'NYC', 'gender': 'F', 'hair': 'blond', 'origin': 'google' } },
    { 'event': 'Purchase', 'properties': { 'city': 'Oakland', 'gender': 'M', 'hair': 'red', 'value': 55, 'item_id': 123 } },
]

def multiseg(data, event, segments):
    # todo: write this    
    pass

output = multiseg(data, 'Signup', ['city', 'gender', 'origin'])
output == \
{
'Signup': {
'NYC': {
'F': {
'google': 2,
'twitter': 1
             },
	     'M': {
	     'twitter': 1
            }
        },
        'Oakland': {
	'M': {
	'google': 1
            }
        }
    }
}

# extra credit: take that data structure and put it in a table
[
     [‘Signup’, ‘NYC’, ‘F’, ‘google’, 2],
     [‘Signup’, ‘NYC’, ‘F’, ‘twitter’, 1],
     [‘Signup’, ‘NYC’, ‘M’, ‘twitter’, 1],
     [‘Signup’, ‘Oakland’, ‘M’, ‘google, 1]
]
*/