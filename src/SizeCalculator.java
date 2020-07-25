import java.io.File;
import java.util.HashMap;

public class SizeCalculator
{
    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};
    private static HashMap<Character, Integer> char2Multiplier = getMultipliers();

    public static String getHumanReadableSize(long size)
    {
        for (int i = 0; i < sizeMultipliers.length; i++)
        {
            double value = size / Math.pow(1024, i);
            if (value < 1024)
            {
                return Math.round(value) + "" + sizeMultipliers[i] + (i > 0 ? "b" : "");
            }
        }
        return "Very big";
    }

    public static long getSizeFromHumanReadable (String size)
    {
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+","")
                .charAt(0);
        int multiplier = char2Multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));
        return length;
    }

    private static HashMap<Character, Integer> getMultipliers()
    {

        HashMap<Character, Integer> char2Multiplier = new HashMap<>();
        for (int i = 0; i < sizeMultipliers.length; i++)
        {
            char2Multiplier.put(
                    sizeMultipliers[i],
                    (int)Math.pow(1024, i)
            );
        }
        return char2Multiplier;
    }

}
