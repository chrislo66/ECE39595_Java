package src;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class DungeonXMLHandler extends DefaultHandler {
	private static final int DEBUG = 1;
        private static final String PROJECTID = "XMLHandler";
	private StringBuilder data = null;
        
        Dungeon dungeon;
        
        //To track the current object being parsed
        private Dungeon dungeonParsed = null;
        private Structure structureParsed = null;
        private Creature creatureParsed = null;
        private Item itemParsed = null;
	private Room roomParsed = null;
        private Passage passageParsed = null;
        private Player playerParsed = null;
	private Monster monsterParsed = null;	
	private CreatureAction creaActParsed = null;	
	private ItemAction itemActParsed = null;
	
	
        ArrayList<Dungeon> dungeons = new ArrayList<Dungeon>();
        ArrayList<Structure> structures = new ArrayList<Structure>();
        ArrayList<Creature> creatures = new ArrayList<Creature>();
        ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<Room> rooms = new ArrayList<Room>();
        ArrayList<Passage> passages = new ArrayList<Passage>();
        ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Monster> monsters = new ArrayList<Monster>();	
	ArrayList<Scroll> scrolls = new ArrayList<Scroll>();
	ArrayList<Armor> armors = new ArrayList<Armor>();
	ArrayList<Sword> swords = new ArrayList<Sword>();

	//booleans
        private boolean bInvisible = false;
	private boolean bVisible = false; 
        private boolean bPosX = false;
        private boolean bPosY = false;
        private boolean bWidth = false;
        private boolean bHeight = false;
        private boolean bType = false;
        private boolean bHp = false;
        private boolean bMaxhit = false;
        private boolean bHpMoves = false;
        private boolean bActionMessage = false;
        private boolean bActionIntValue = false;
        private boolean bActionCharValue = false;
        private boolean bItemIntValue = false;
	
	public ArrayList<Dungeon> getDungeons(){
            return dungeons;
	}
        public ArrayList<Item> getItems(){
            return items;
	}
	public ArrayList<Room> getRooms() {
            return rooms;
        }
	public ArrayList<Passage> getPassages() {
            return passages;
	}
        public ArrayList<Player> getPlayers(){
            return players;
	}
	public ArrayList<Monster> getMonsters() {
            return monsters;
	}

	public ArrayList<Armor> getArmors(){
            return armors;
	}
	public ArrayList<Scroll> getScrolls() {
            return scrolls;
	}
	public ArrayList<Sword> getSwords(){
            return swords;
	}
	
	public DungeonXMLHandler() {
	}
        
	
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	if(DEBUG > 1){
            System.out.println(PROJECTID + ".startElement qName: " + qName);
        }
        
        if(qName.equalsIgnoreCase("Dungeon")){
            String dgName = attributes.getValue("name"); //String
            int dgWidth = Integer.parseInt(attributes.getValue("width"));
            int dgTopHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int dgGameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int dgBottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));

            Dungeon dungeon = new Dungeon(dgName, dgWidth, dgGameHeight, dgTopHeight, dgBottomHeight);
            dungeonParsed = dungeon; 
            dungeons.add(dungeonParsed);
        } 
        else if(qName.equalsIgnoreCase("Passages")){
            
        }
        else if(qName.equalsIgnoreCase("Passage")){
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage();
            passage.setID(room1, room2);
            dungeonParsed.addPassage(passage);
            structureParsed = passage;
            passageParsed = passage;
            structures.add(passage);
            passages.add(passage);    
        }
        else if(qName.equalsIgnoreCase("Rooms")){

        }
        else if(qName.equalsIgnoreCase("Room")) {
            int roomNum = Integer.parseInt(attributes.getValue("room"));
            Room room = new Room(); 
            room.setId(roomNum);
            structureParsed = room;
            roomParsed = room;
            dungeonParsed.addRoom(room);
            structures.add(room); 
            rooms.add(room);    
	}
        else if(qName.equalsIgnoreCase("Monster")) {	
            String monsName = attributes.getValue("name");
            int monsRoom = Integer.parseInt(attributes.getValue("room"));
            int monsSerial = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster();
            monster.setName(monsName);
            monster.setID(monsRoom, monsSerial); 
            creatureParsed = monster;
            monsterParsed = monster;
            dungeonParsed.addCreature(monster);
            creatures.add(monster); 
            monsters.add(monster);
            
	}
        else if(qName.equalsIgnoreCase("Player")) {
            String playerName = attributes.getValue("name");
            int playerRoom = Integer.parseInt(attributes.getValue("room"));
            int playerSerial = Integer.parseInt(attributes.getValue("serial"));
            Player player = new Player();
            player.setName(playerName);
            player.setID(playerRoom, playerSerial);
            creatureParsed = player;
            playerParsed = player;
            dungeonParsed.addCreature(player);
            creatures.add(player);
            players.add(player);
            //player.setType('@');
		
        }
        else if(qName.equalsIgnoreCase("Armor")) {
            String armorName = attributes.getValue("name");
            int armorRoom = Integer.parseInt(attributes.getValue("room"));
            int armorSerial = Integer.parseInt(attributes.getValue("serial"));
            Armor armor = new Armor(armorName);
            armor.setID(armorRoom, armorSerial);
            dungeonParsed.addItem(armor);
            itemParsed = armor;
            if (playerParsed != null) {
                armor.setOwner(playerParsed);
                Rogue.getPack().add(armor);
            } else {
                armors.add(armor);
            }

	}
        else if(qName.equalsIgnoreCase("Sword")) {
            String swordName = attributes.getValue("name");
            int swordRoom = Integer.parseInt(attributes.getValue("room"));
            int swordSerial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(swordName);
            sword.setID(swordRoom, swordSerial);
            dungeonParsed.addItem(sword);
            itemParsed = sword;
            if (playerParsed != null) {
                sword.setOwner(playerParsed);
                Rogue.getPack().add(sword);
            } else {
                swords.add(sword);
            }
			
	}
        else if(qName.equalsIgnoreCase("Scroll")) {
            String scrollName = attributes.getValue("name");
            int scrollRoom = Integer.parseInt(attributes.getValue("room"));
            int scrollSerial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(scrollName);
            scroll.setID(scrollRoom, scrollSerial);
            dungeonParsed.addItem(scroll);
            itemParsed = scroll;
            scrolls.add(scroll);
	}
        else if(qName.equalsIgnoreCase("CreatureAction")) {
            String actNameC = attributes.getValue("name");
            String actTypeC = attributes.getValue("type");
            /*
            CreatureAction creatureAction = null;
            if (creatureParsed != null) {
                CreatureAction ca = new CreatureAction(creatureParsed);
                ca.setAction(actNameC);
                ca.setType(actTypeC);
                switch (actNameC) {
                    case "Remove":
                        creatureAction = new Remove(creatureParsed);
                        break;
                    case "Teleport":
                        creatureAction = new Teleport(creatureParsed);
                        break;
                    case "DropPack":
                        creatureAction = new DropPack(creatureParsed);
                        break;
                    case "ChangedDisplayedType":
                        creatureAction = new ChangedDisplayedType(creatureParsed);
                        break;
                    case "UpdateDisplay":
                        creatureAction = new UpdateDisplay(creatureParsed);
                        break;
                    case "YouWin":
                        creatureAction = new YouWin(creatureParsed);
                        break;
                    case "EndGame":
                        creatureAction = new EndGame(creatureParsed);
                        break;
                    default:
                        System.out.println("Unknown CreatureAction name: " + actNameC);
                        break;
                }
                creaActParsed = creatureAction;
                Creature typeC = new Creature();
                switch (actTypeC) {
                    case "death":
                        typeC.setDeathAction(creaActParsed);
                        break;
                    case "hit":
                        typeC.setHitAction(creaActParsed);
                        break;
                    default:
                        System.out.println("Unknown CreatureAction type: " + actTypeC);
                        break;
                }
                //actionParsed = ca;
            
                
            }*/
            if (playerParsed != null) {
                CreatureAction creatureAction = new CreatureAction(playerParsed);
                creatureAction.setAction(actNameC);
                if (actTypeC.equals("death")) {
                    playerParsed.addDeathAction(creatureAction);
                } else if (actTypeC.equals("hit")) {
                    playerParsed.addHitAction(creatureAction);
                }
                creaActParsed = creatureAction;
            } else if (monsterParsed != null) {
                CreatureAction creatureAction = new CreatureAction(monsterParsed);
                creatureAction.setAction(actNameC);
                if (actTypeC.equals("death")) {
                    monsterParsed.addDeathAction(creatureAction);
                } else if (actTypeC.equals("hit")) {
                    monsterParsed.addHitAction(creatureAction);
                }
                creaActParsed = creatureAction;
            }
            
	}
        else if(qName.equalsIgnoreCase("ItemAction")) {
            ItemAction itemAction = new ItemAction(itemParsed);
            itemAction.setAction(attributes.getValue("name"));
            itemParsed.addItemAction(itemAction);
            itemActParsed = itemAction;
        }
        else if(qName.equalsIgnoreCase("actionMessage")){
            bActionMessage = true;
        }
        else if(qName.equalsIgnoreCase("actionIntValue")){
            bActionIntValue = true;
        }
        else if(qName.equalsIgnoreCase("actionCharValue")){
            bActionCharValue = true;
        }
        else if(qName.equalsIgnoreCase("ItemIntValue")){
            bItemIntValue = true;
        }
        else if(qName.equalsIgnoreCase("visible")){
            bVisible = true;
        } 
        else if(qName.equalsIgnoreCase("posX")){
            bPosX = true;
        } 
        else if(qName.equalsIgnoreCase("posY")){
            bPosY = true;
        } 
        else if(qName.equalsIgnoreCase("width")){
            bWidth = true;
        } 
        else if(qName.equalsIgnoreCase("height")){
            bHeight = true;
        } 
        else if(qName.equalsIgnoreCase("hp")){
            bHp = true;
        } 
        else if(qName.equalsIgnoreCase("maxhit")){
            bMaxhit = true;
        } 
        else if(qName.equalsIgnoreCase("hpMoves")){
            bHpMoves = true;
        }
        else if(qName.equalsIgnoreCase("type")){
            bType = true;
        }
        else{
            System.out.println("Unknown qname: " + qName);
        }
        
        data = new StringBuilder();
    }
    
@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bActionCharValue) {
            if (creaActParsed != null) {
                creaActParsed.setCharValue(data.toString().charAt(0));
                bActionCharValue = false;
            } else if (itemActParsed != null) {
                itemActParsed.setCharValue(data.toString().charAt(0));
                bActionCharValue = false;
            }
        } else if (bActionMessage) {
            if (creaActParsed != null) {
                creaActParsed.setMessage(data.toString());
                bActionMessage = false;
            } else if (itemActParsed != null) {
                itemActParsed.setMessage(data.toString());
                bActionMessage = false;
            }
        } else if (bActionIntValue) {
            if (creaActParsed != null) {
                creaActParsed.setIntValue(Integer.parseInt(data.toString()));
                bActionIntValue = false;
            } else if (itemActParsed != null) {
                itemActParsed.setIntValue(Integer.parseInt(data.toString()));
                bActionIntValue = false;
            }
        } else if (bItemIntValue) {
            if (itemParsed != null) {
                itemParsed.setIntValue(Integer.parseInt(data.toString()));
                bItemIntValue = false;
            }
        } else if (bPosX) {
            if (itemParsed != null) {
                itemParsed.SetPosX(Integer.parseInt(data.toString()));
                itemParsed.setUniPosX(Integer.parseInt(data.toString()) + roomParsed.getPosX());
                bPosX = false;
            } else if (monsterParsed != null) {
                monsterParsed.SetPosX(Integer.parseInt(data.toString()));
                bPosX = false;
            } else if (playerParsed != null) {
                playerParsed.SetPosX(Integer.parseInt(data.toString()));
                bPosX = false;
            } else if (roomParsed != null) {
                roomParsed.SetPosX(Integer.parseInt(data.toString()));
                bPosX = false;
            } else if (passageParsed != null) {
                passageParsed.addCornerPosX(Integer.parseInt(data.toString()));
                bPosX = false;
            }

        } else if (bPosY) {
            if (itemParsed != null) {
                itemParsed.SetPosY(Integer.parseInt(data.toString()));
                itemParsed.setUniPosY(Integer.parseInt(data.toString()) + roomParsed.getPosY() + dungeonParsed.getTopHeight());
                bPosY = false;
            } else if (monsterParsed != null) {
                monsterParsed.SetPosY(Integer.parseInt(data.toString()));
                bPosY = false;
            } else if (playerParsed != null) {
                playerParsed.SetPosY(Integer.parseInt(data.toString()));
                bPosY = false;
            } else if (roomParsed != null) {
                roomParsed.SetPosY(Integer.parseInt(data.toString()));
                bPosY = false;
            } else if (passageParsed != null) {
                passageParsed.addCornerPosY(Integer.parseInt(data.toString()));
                bPosY = false;
            }

        } else if (bWidth) {
            if (structureParsed != null && creatureParsed == null && itemParsed == null) {
                structureParsed.SetWidth(Integer.parseInt(data.toString()));
                bWidth = false;
            }
        } else if (bHeight) {
            if (structureParsed != null && creatureParsed == null && itemParsed == null) {
                structureParsed.SetHeight(Integer.parseInt(data.toString()));
                bHeight = false;
            }
        } else if (bHp) {
            if (creatureParsed != null) {
                creatureParsed.setHp(Integer.parseInt(data.toString()));
                bHp = false;
            }
        } else if (bMaxhit) {
            if (creatureParsed != null) {
                creatureParsed.setMaxHit(Integer.parseInt(data.toString()));
                bMaxhit = false;
            }
        } else if (bHpMoves) {
            if (creatureParsed != null) {
                creatureParsed.setHpMove(Integer.parseInt(data.toString()));
                bHpMoves = false;
            }
        } else if (bType) {
            if (creatureParsed != null) {
                creatureParsed.setType(data.toString().charAt(0));
                bType = false;
            }
        } else if (qName.equalsIgnoreCase("Room") || qName.equalsIgnoreCase("Passage")) {
            structureParsed = null;
        } else if (qName.equalsIgnoreCase("Monster") || qName.equalsIgnoreCase("Player")) {
            creatureParsed = null;
        } else if (qName.equalsIgnoreCase("Armor") || qName.equalsIgnoreCase("Scroll") || qName.equalsIgnoreCase("Sword")) {
            itemParsed = null;
        } else if (qName.equalsIgnoreCase("Room")) {
            roomParsed = null;
        } else if (qName.equalsIgnoreCase("Passage")) {
            passageParsed = null;
        } else if (qName.equalsIgnoreCase("Monster")) {
            monsterParsed = null;
        } else if (qName.equalsIgnoreCase("Player")) {
            playerParsed = null;
        } else if (qName.equalsIgnoreCase("ItemAction")) {
            itemActParsed = null;
        } else if (qName.equalsIgnoreCase("CreatureAction")) {
            creaActParsed = null;
        }
    }
	@Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}



