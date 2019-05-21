import java.util.Arrays;

//creating class for hashtable
public class hash {

    String[] myArray;
    int arraySize;
    int arrayItem = 0;

    public static void main(String[] args) {

        hash newFunction = new hash(30);

        // Simplest hash function
        // Mod Hash function
        // This contains 30 items to show how collisions happen

        String[] elements = { "100", "510", "170", "214", "268", "398",
            "235", "802", "900", "723", "699", "1", "16", "999", "890",
            "725", "998", "978", "988", "990", "989", "984", "320", "321",
            "400", "415", "450", "50", "660", "624" };

        newFunction.hashFunction2(elements, newFunction.myArray);

        //now locate the value of suppose 660 in the hash table!
        newFunction.findKey("660");
        newFunction.displayStack();
    }

    // simple hash function that puts values in the same index that matches their value
    public void hashFunction1(String[] arrayStrings, String[] myArray)
    {
        for (int n = 0; n < arrayStrings.length; n++)
        {
            String elementVal = arrayStrings[n];

            myArray[Integer.parseInt(elementVal)] = elementVal;
        }
    }

    //here we will use the mod function to fit these numbers into a 30 item array
    //we will have to take the modulus of the value versus the array size

    //our goal here is to make the array big enough in size so it can help avoid collisions
    //but not so big to the point that there will be empty spaces or waste memory
    public void hashFunction2(String[] arrayString, String[] myArray)
    {
        for (int n = 0; n < arrayString.length; n++)
        {
            String elementVal = arrayString[n];

            // here we create an index to store the value in by taking the modulus
            int arrayIndex = Integer.parseInt(elementVal) % 29;
            System.out.println("Modulus index= " + arrayIndex + " for value " + elementVal);

            //cycle through the array until we find an empty space
            while (myArray[arrayIndex] != "-1")
            {
                ++arrayIndex;
                System.out.println("Collision Try" + arrayIndex + " instead");
                //if we go to the end of the array then go back to index 0
                arrayIndex %= arraySize;
            }
            myArray[arrayIndex] = elementVal;
        }
    }

    //returning the value stored in the Hash table
    public String findKey(String key)
    {
        //finding the keys original hash key
        int arrayIndexHash = Integer.parseInt(key) % 29;

        while(myArray[arrayIndexHash] != "-1")
        {
            if(myArray[arrayIndexHash] == key)
            {
                //found the key so return it
                System.out.println(key + "was found in index" + arrayIndexHash);

                return myArray[arrayIndexHash];
            }

            // look in the next index
            ++arrayIndexHash;
            // If we get to the end of the array then return back to index 0
            arrayIndexHash %= arraySize;
        }
        return null;
    }

    hash(int size)
    {
        arraySize = size;
        myArray = new String[size];
        Arrays.fill(myArray, "-1");
    }

    //here we will display our stack
    public void displayStack()
    {
        int increment = 0;

        for(int m = 0; m < 3; m++)
        {
            increment += 10;

            for(int n = 0; n < 71; n++)
                System.out.print("-");

            System.out.println();

            for(int n = increment - 10; n < increment; n++)
            {
                System.out.format("| %3s " + " ", n);
            }

            System.out.println("|");

            for (int n = 0; n < 71; n++)
                System.out.print("-");

            System.out.println();

            for(int n = increment - 10; n < increment; n++)
            {
                if (myArray[n].equals("-1"))
                    System.out.println("|   ");
                else
                    System.out.print(String.format("| %3s " + " ", myArray[n]));
            }

            System.out.println("|");

            for(int n = 0; n < 71; n++)
                System.out.print("-");

            System.out.println();
        }
    }
}
