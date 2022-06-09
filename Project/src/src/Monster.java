
package src;
public class Monster extends Creature{	
    int serial;
    int maxHit;
	
    Monster() {
    }

    void setName(String _name){
        name = _name;
    }

    void setID(int _room, int _serial){
        room = _room;
        serial = _serial;
    }

    void setMaxHit(int _maxHit){
        maxHit = _maxHit;
    }
    
    int getID(){
        return serial;
    }
    int getMaxHit(){
        return maxHit;
    }

    
}