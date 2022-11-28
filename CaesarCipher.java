
/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class CaesarCipher 
{
    private String alphabet;
    private String shiftedAlphabet;
    private int    mainKey;

    public CaesarCipher(int key)
    {
        mainKey         = key;
        alphabet        = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    public String encrypt(String input)
    {
        StringBuilder output   = new StringBuilder("");

        for(int i = 0; i < input.length(); i++)
        {
            char currentChar = input.charAt(i);

            if(Character.isUpperCase(currentChar)) // Character is uppercase
            {
                int positionOfCurrentChar = alphabet.indexOf(currentChar);

                if(positionOfCurrentChar != -1)
                {
                    char cryptedChar = shiftedAlphabet.charAt(positionOfCurrentChar);
                    output.append(cryptedChar);
                }
                else
                {
                    output.append(currentChar);
                }
            }
            else if(Character.isLowerCase(currentChar)) // Character is lowercase
            {
                int positionOfCurrentChar = alphabet.indexOf(Character.toUpperCase(currentChar));

                if(positionOfCurrentChar != -1)
                {
                    char cryptedChar = Character.toLowerCase(shiftedAlphabet.charAt(positionOfCurrentChar));
                    output.append(cryptedChar);
                }
                else
                {
                    output.append(currentChar);
                }
            }
            else // Character is not letter (It's punctuation or digit)
            {
                output.append(currentChar);
            }
        }

        return output.toString();
    }
    
    public String decrypt(String input)
    {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        
        return cc.encrypt(input);
    }  
}
