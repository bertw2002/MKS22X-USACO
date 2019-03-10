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
      //calculate volume
      int depth = 0;
      for(int x = 0; x < R; x++){
        for(int y = 0; y < C; y++){
        	if(lake[x][y] < E){
        		depth += E - lake[x][y];
        	}
        }
      }
      return 72 * 72 * depth;
    }
    catch(FileNotFoundException e){System.out.println("bad filename");return -100000;}
  }



  //helper for silver making old and new arrays equal
  private static void setEqual(int[][] first, int[][] second){
    for (int x = 0; x < first.length; x++) {
      for (int y = 0; y < first[0].length; y++) {
        second[x][y] = first[x][y];
      }
    }
  }
  public static int silver(String filename){
    try{
      //set up local variables
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      int N = Integer.parseInt(scanner.next());
      int M = Integer.parseInt(scanner.next());
      int T = Integer.parseInt(scanner.next());
      //initialize pasture.
      String nextline = scanner.nextLine();
      String[][] pasture =  new String[N][M];
      for (int x = 0; x < N; x++){
        nextline = scanner.nextLine();
        for (int y = 0; y < M; y++){
          pasture[x][y] = nextline.substring(y, y + 1);
        }
      }
      //instructions.
      int[] instr = new int[4];
      for (int x = 0; x < instr.length; x++) {
        instr[x] = scanner.nextInt();
      }
      //do instructions.
      int[][] newpasture = new int[N][M];
      int[][] oldpasture = new int[N][M];
      for (int x = 0; x < N; x++){
        for (int y = 0; y < M; y++){
          if (pasture[x][y].equals("*")){
            newpasture[x][y] = -1;
            oldpasture[x][y] = -1;
          }else{
            newpasture[x][y] = 0;
            oldpasture[x][y] = 0;
          }
        }
      }
      //testing purposes
      /*
      for (int x = 0; x < N; x++){
        for (int y = 0; y < M; y++){
          System.out.print(newpasture[x][y]);
        }
        System.out.println();
      }*/
      newpasture[instr[0] - 1][instr[1] - 1] = 1;
      for (int time = 0; time < T; time++){
        setEqual(newpasture, oldpasture);
        
        for (int x = 0; x < N; x++) {
          for (int y = 0; y < M; y++) {

            if (newpasture[x][y] != -1) {
              newpasture[x][y] = 0;

              if (y + 1 < M && newpasture[x][y + 1] >= 0) {
                newpasture[x][y] += oldpasture[x][y + 1];
              }

              if (y - 1 >= 0 && newpasture[x][y - 1] >= 0) {
                newpasture[x][y] += oldpasture[x][y - 1];
              }

              if (x - 1 >= 0 && newpasture[x - 1][y] >= 0) {
                newpasture[x][y] += oldpasture[x - 1][y];
              }

              if (x + 1 < N && newpasture[x + 1][y] >= 0) {
                newpasture[x][y] += oldpasture[x + 1][y];
              }
            }
          }
        }
      }
      return newpasture[instr[2] - 1][instr[3] - 1];
    }
    catch(FileNotFoundException e) {System.out.println("bad filename");return -100000;}
  }

  public static void main(String[] args) {
   System.out.println("Bronze\n");
   System.out.println(USACO.bronze("test1.txt"));
   System.out.println("should be 342144\n");
   System.out.println(USACO.bronze("test2.txt"));
   System.out.println("should be 102762432\n");
   System.out.println("Silver\n");
   System.out.println(USACO.silver("silvertest1.txt"));
   System.out.println("answer is 1\n");
   System.out.println(USACO.silver("silvertest2.txt"));
   System.out.println("answer is 74\n");
  }


}
