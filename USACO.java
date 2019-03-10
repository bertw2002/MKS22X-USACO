import java.util.Scanner;
import java.util.*;
import java.io.*;
public class USACO{
  public static int bronze(String filename){
    try{
      //set up local variables
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      int R = Integer.parseInt(scanner.next());
      int C = Integer.parseInt(scanner.next());
      int E = Integer.parseInt(scanner.next());
      int N = Integer.parseInt(scanner.next());
      // initialize lake
      int[][] lake =  new int[R][C];
      for (int x = 0; x < lake.length; x++){
        for (int y = 0; y < lake[0].length; y++){
          lake[x][y] = Integer.parseInt(scanner.next());
        }
      }
      //instructions
      int[][] instr = new int[N][3];
      for (int x = 0; x < instr.length; x++) {
        for (int y = 0; y < instr[x].length; y++) {
          instr[x][y] = scanner.nextInt();
        }
      }
      //do the instructions...
      int biggest;
      for (int counter = 0; counter < instr.length; counter++){
        biggest = lake[instr[counter][0]][instr[counter][1]];
        //find biggest
        for (int x = instr[counter][0] - 1; x < instr[counter][0] + 2; x++) {
          for (int y = instr[counter][1] - 1; y < instr[counter][1] + 2; y++) {
            if (lake[x][y] > biggest) {
              biggest = lake[x][y];
            }
          }
        }
        //set new lake elevations after stomp.
        int newbig = biggest - instr[counter][2];
        for (int x = instr[counter][0] - 1; x < instr[counter][0] + 2; x++) {
          for (int y = instr[counter][1] - 1; y < instr[counter][1] + 2; y++) {
            if (lake[x][y] > newbig) {
              lake[x][y] = newbig;
            }
          }
        }
      }
      int sum = 0;
      for(int x = 0; x < R; x++){
        for(int y = 0; y < C; y++){
        	if(lake[x][y] < E){
        		sum += E - lake[x][y];
        	}
        }
      }
      return sum * 72 * 72;
    }
    catch(FileNotFoundException e){System.out.println("bad filename");return -100000;}
  }
  //public static int silver(String filename);
  public static void main(String[] args) {
       System.out.println("Bronze\n");
       System.out.println(USACO.bronze("test1.txt"));
       System.out.println("should be 342144.\n");
       System.out.println(USACO.bronze("test2.txt"));
       System.out.println("Correct answer is 102762432.\n");
       }


}
