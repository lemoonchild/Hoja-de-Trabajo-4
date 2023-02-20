package structure5;


public class Version {

   public static final String Id = "$Id: Version.java 36 2007-08-29 15:39:24Z bailey $";
   public static final String name = "structure";
   public static final String author = "duane a. bailey";


   public static void main(String[] args) {
      String s = "$Id: Version.java 36 2007-08-29 15:39:24Z bailey $";
      int s1 = s.indexOf(" ", s.indexOf(" ") + 1);
      int s2 = s.indexOf(" ", s1 + 1);
      int major = Integer.valueOf(s.substring(s1 + 1, s2)).intValue();
      int c = s.indexOf(":", s.indexOf(":", s2) + 1);
      String date = s.substring(s2 + 1, c);
      int year = Integer.valueOf(date.substring(0, 4)).intValue();
      String info = "package structure, version " + major + " (" + date + "), (c) 1997-" + year + " " + "duane a. bailey";
      if(args.length != 0) {
         if(args[0].equals("-d")) {
            System.out.println(date);
         } else if(args[0].equals("-M")) {
            System.out.println(major);
         } else if(args[0].equals("-p")) {
            System.out.println("structure");
         } else if(args[0].equals("-a")) {
            System.out.println("duane a. bailey");
         }
      } else {
         System.out.println(info);
      }

   }
}
