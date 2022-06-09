/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author christina
 */
public class LongList implements MyList{
    private long data;
    private LongList next;
    
    public LongList(LongList n, long data){
        next = n;
        this.data = data;
    }
    public long getData(){
        return data;
    }
    public LongList next(){
        return next;
    }
    public void printNode(){
        System.out.println("LongList Node, data is: " + getData());
    }
    
}
