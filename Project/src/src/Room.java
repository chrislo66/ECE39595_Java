package src;
public class Room extends Structure{
    int roomID;
    
    Room() {    
    }
    
    Room(String s){
        roomID = Integer.parseInt(s);
    }
    void setId(int room){
        roomID = room;
    }

    void setCreature(Creature monster){ 
    }

    int getId(){
        return roomID;
    }
}