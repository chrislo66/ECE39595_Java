public class Point extends ComparableArray {

   // create constructors as needed.
    public Point (int data1, int data2) {
        super(new int[] {data1, data2}); //be careful here
    }

    public Point (ComparableArray points) {
        super(points);
    }
   /*
    * haschCode( ) should return an int based on the values of the object fields
    * equals( ) should return true if two objects are equal based on the values of the
    * object fields.  Given IntArray objects i1, i2 and i3, it must also be the case 
    * that 
    * 1. if i1.equals(i2) == i2.equals(i1).
    * 2. i1.equals(i1) is true, for any i1
    * 3. if i1.equals(i2) is true, and i2.equals(i3) is true, then it must be that
    *    i1.equals(i3) is true.
    * 3. if i1.equals(i2) is true, then i1.hashCode( ) == i2.hashCode( )
    *
    * Note that if i1.equals(i2) is false, then i1.hashCode( ) can be equal or not
    *      equal to i2.hashCode( ).
    *
    * As long as these rules are followed, you can implement hashCode( ) and equals( )
    * any way you want.
    */
    @Override
    public int hashCode( ){
       int sum = 0;
       for (int i=0; i < ary.length; i++) {
           sum += ary[i] * i;
       }
       return sum;
    }
    
    @Override
    public boolean equals(Object a){
        if (a == null) {return false;}
        if (this == a) {return true;} //if they are pointing to the same object
        if (a instanceof ComparableArray) {
            int comp = ((ComparableArray)a).compareTo((ComparableArray)this);
            if (comp == 0) {return true;}
        }
        return false;
    }
}
