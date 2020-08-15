package my_methods;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyFaker {
    public String loc(String locale){
        String str;
        if(locale.equals("en_US"))
            str="en-US";
        else if(locale.equals("ru_RU"))
            str="ru";
        else str="by";
        return str;

    }
    public void faker(String locale, int numberOfLines, double numberOfErrors) {
        List<String> list = new ArrayList();
        Faker faker = new Faker(new Locale(locale));
        String line;
   for(int i=0; i<numberOfLines;i++)
        {
            line=generateLine(faker);
            if(locale.equals("ru")||locale.equals("by")){
            int k=100000+(int)(Math.random()*(899999));
            line=line.replace("і","i");
            line=line.replace("І","I");
            String s=String.valueOf(k);
            line=line.replace("######",s);
            }
            if(numberOfErrors>1)
            {
                for (int c = 0; c < numberOfErrors; c++)
                    line = generateType(line, locale);
            }
            list.add(line);
        }
        if(numberOfErrors>0&&numberOfErrors<1){
        int n= (int) (numberOfLines*numberOfErrors);
            for (int i = 0; i < n; i++) {
                int k = (int) (Math.random() * (numberOfLines));
                line = generateType(list.get(k), locale);
                list.set(k, line);
            }
        }
        for(int i=0;i<numberOfLines;i++)
            System.out.println(list.get(i));
    }
    public String generateLine(Faker faker){
        return faker.name().fullName()+";"+faker.address().fullAddress()+"; "+faker.phoneNumber().phoneNumber();
    }

    public String generateType(String line,String locale){
        int a=(int) (Math.random() * 3);
        int position=0;
        if(line.length()==0||line.length()==1||line.length()==2)
            a=1;
        else
            position=(int)(Math.random()*(line.length()-1));
        String l=new String();
        switch (a) {
            case 0:l=delete(line,position);break;
            case 1:l=permutation(line,position);break;
            case 2:l=addSymbol(line,locale,position);break;
        }
        return l;
    }

    private String addSymbol(String line, String locale, int position) {
        int a=(int) (Math.random() * 2);
        String l=new String();
        switch (a){
            case 0: l=addLetter(line,locale,position);break;
            case 1: l=addNumber(line,position);break;
        }
        return l;
    }

    private String addLetter(String line, String locale, int position) {
        String l;
        if(locale=="ru")
           l= addRusChar(line,position);
        else if(locale=="en-US")
            l=addEngChar(line,position);
        else l=addBelChar(line,position);
        return l;
    }

    private String addEngChar(String line, int position) {
        int k=97+(int)(Math.random()*26);
        char c=(char)k;
        String l=addChar(line,c,position);
        return l;
    }

    private String addBelChar(String line, int position) {
        String ab=" йціуўкенгшзх'фывапролджэячсмитьбю";
        int k=(int)(Math.random()*ab.length());
        char c=ab.charAt(k);
        String l=addChar(line,c,position);
        return l;
    }

    private String addRusChar(String line, int position) {
        String ab=" йцукенгшщзхъфывапролджэячсмитьбю";
        int k=(int)(Math.random()*ab.length());
        char c=ab.charAt(k);
        String l=addChar(line,c,position);
        return l;
    }

    private String addNumber(String line, int position) {
        int n=(int) (Math.random() * 10);
        char c=(char)(n+'0');
        String l=addChar(line,c,position);
        return l;
    }

    private String permutation(String line,int position) {
        String l=swapString(line,position);
        return l;
    }

    private String swapString(String line, int position) {
        char[] c = line.toCharArray();
        char temp = c[position];
        c[position] = c[position+1];
        c[position+1] = temp;
        String swappedString = new String(c);
        return swappedString;
    }

    private String delete(String line, int position) {
        String l=removeCharAt(line,position);
        return l;
    }
    public static String removeCharAt(String s, int position) {
        return s.substring(0, position) + s.substring(position + 1);
    }
    public String addChar(String str, char ch, int position) {
        return str.substring(0, position) + ch + str.substring(position);
    }
}
