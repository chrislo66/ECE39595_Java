package src;
public class Player extends Creature{
    char type;
    int serial;
    int maxHit;
    Item weapon;
    Item armor;
	
    public Player(){
        type = '@';
    }
    
    void setName(String _name){
        name = _name;
    }

    void setID(int _room, int _serial){
        room = _room;
        serial = _serial;
    }
	
    void setWeapon(Item _sword){
        weapon = _sword;
    }

    void setArmor(Item _armor){
        armor = _armor;
    }
    void setMaxHit(int _maxHit){
        maxHit = _maxHit;
    }
	
    Item getWeapon() {
        return weapon;
    }

    Item getArmor() {
        return armor;
    }
    
    int getMaxHit(){
        return maxHit;
    }
}