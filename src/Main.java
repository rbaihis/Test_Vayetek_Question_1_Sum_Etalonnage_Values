
import java.io.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    private static int getEtalonnageValue(String line, StringBuilder digitsHolder){

        digitsHolder.setLength(0);

        for (int i=0 ; i<line.length(); i++)
            if(Character.isDigit(line.charAt(i)))
                digitsHolder.append(line.charAt(i));

        if(digitsHolder.isEmpty())
            return 0;

        int firstDigit =  Character.getNumericValue(digitsHolder.charAt(0));
        int lastDigit =  Character.getNumericValue(digitsHolder.charAt(digitsHolder.length()-1));

        return firstDigit*10 + lastDigit;
    }


    public static void main(String[] args) throws IOException {

        String filePath = "resources/document.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){

            StringBuilder reusableDigitHolder = new StringBuilder();
            int sum = br.lines()
                    .mapToInt( line-> getEtalonnageValue(line,reusableDigitHolder))
                    .sum();

            log.info(() -> String.format("Sum = %d", sum));

        }catch (FileNotFoundException e){
            throw new FileNotFoundException("Error occurred while Opening File resources/document.txt");

        }catch (UncheckedIOException e){
            throw new UncheckedIOException("Error occurred while reading file lines", e.getCause());
        }
    }

}
