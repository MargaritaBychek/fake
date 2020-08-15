import my_methods.*;

public class MainClass {
    public static void main(String[] args)
    {
        double startTime = System.currentTimeMillis();
        MyFaker myfaker=new MyFaker();
        String locale=new String();
        int numberOfLines=0;
        double numberOfErrors=0;
        try {
            if(args.length==0) {
            System.out.println("Empty arguments");
                System.exit(0);
            }
            else {
            if(checkLang(args[0]))
                locale=myfaker.loc(args[0]);
            else System.exit(0);
            if(args.length==1){
                System.out.println("empty number of lines");
                System.exit(0);
            }
            else {
                if (checkNumber(args[1]))
                    numberOfLines = Integer.parseInt(args[1]);
                else {
                    System.out.println("wrong format");
                    System.exit(0);
                }
            }
            if(args.length==2)
                numberOfErrors=0;
            else if(checkNumber(args[2]))
                numberOfErrors=Double.parseDouble(args[2]);
            else System.exit(0);}
        }
        finally {
          myfaker.faker(locale,numberOfLines,numberOfErrors);
            double timeSpent = System.currentTimeMillis() - startTime;
            timeSpent=timeSpent/60000;
            System.out.println("Time: "+timeSpent +" m");
        }
    }

    private static boolean checkNumber(String arg) {
        return arg.matches("\\d+(\\.\\d+)?");
    }

    private static boolean checkLang(String arg) {
        if (arg.equals("en_US") || arg.equals("ru_RU") || arg.equals("be_BY"))
            return true;
        System.out.println("LanguageError "+arg+";");
        return false;
    }

}
