import edu.duke.*;

/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class TestCaesarCipher 
{

    public int[] countLetters(String encrypted)
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[]  counts   = new int[26];

        for(int i = 0; i < encrypted.length(); i++)
        {
            char ch              = Character.toUpperCase(encrypted.charAt(i));
            int  indexOfThisChar = alphabet.indexOf(ch);

            if(indexOfThisChar != -1)
            {
                counts[indexOfThisChar]++;
            }
        }

        for(int i = 0; i < counts.length; i++)
        {
            System.out.println("Cell " + i + "(" + alphabet.charAt(i) + ") is: " + counts[i]);
        }

        System.out.println();

        return counts;
    }

    public int indexOfMax(int[] counts)
    {
        int maxIndex = 0;

        for(int i = 0; i < counts.length; i++)
        {
            if(counts[i] > counts[maxIndex])
            {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public void simpleTests()
    {
        FileResource fr    = new FileResource();
        String       input = fr.asString();

        CaesarCipher cc        = new CaesarCipher(18);
        String       encrypted = cc.encrypt(input);
        System.out.println("Encrypted Message is: " + encrypted);

        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted Message is: " + decrypted);
        
        String bbc = breakCaesarCipher(encrypted); 
        System.out.println("BBC: " + bbc);
    }

    public String breakCaesarCipher(String input)
    {
        int[] frequent = countLetters(input);
        int   maxIndex = indexOfMax(frequent);
        int   dKey     = maxIndex - 4;

        if(maxIndex < 4)
        {
            dKey = 26 - (4 - maxIndex);
        }

        CaesarCipher cc = new CaesarCipher(dKey);
        
        return cc.decrypt(input);
    }
}
