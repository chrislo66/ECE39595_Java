/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author christina
 */
public class IntList implements MyList{
    private int data;
    private IntList next;
    
    public IntList(IntList n, int data){
        next = n;
        this.data = data;
    }
    public int getData(){
        return data;
    }
    public IntList next(){
        return next;
    }
    public void printNode(){
        System.out.println("IntList Node, data is: " + getData());
    }
    
}

