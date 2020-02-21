//import java.util.Scanner;
import java.io.*;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.HashMap;
//import java.util.PriorityQueue;
//import java.io.FileWriter;
import java.util.*;

class PartA{


  public static void main(String[] args){

    if(args.length == 0){
      System.out.println("usage: java PartA filename");
      return;
    }

    File filename = new File(args[0]);
    Scanner in;

    try{
      in = new Scanner(filename);
    } catch(FileNotFoundException fnfe){
    System.out.println("Error: file not found");
      return;
    }

    FileWriter output;
    try{
      output = new FileWriter("outputa.txt");
    } catch(IOException ioe){
      try{
	output = new FileWriter("NextBillGates.txt");
      } catch(IOException ioe2){
	System.out.println("Error writing file");
	return;
      }
    }

    /**   //   **/
    
    int numBooks = 0, numLibs = 0, numDays = 0;

    numBooks = in.nextInt();
    numLibs = in.nextInt();
    numDays = in.nextInt();

    //System.out.println(numBooks + " " + numLibs + " " + numDays);
    //System.out.println("Number of libs: " + numLibs);
    PriorityQueue<Library> libs = new PriorityQueue<Library>(numLibs, new LibSignUpComp());
    HashMap<Integer,Integer> allBooks = new HashMap<Integer,Integer>();
    int[] bookMemory = new int[numBooks];
    int bmi = 0;

    //read in all book scores
    for(int i = 0; i < numBooks; i++){
      allBooks.put(i, in.nextInt());
    }

    //read in each library
    int lbooks = 0, lsignup = 0, lscan = 0;

    for(int i = 0; i < numLibs; i++){
      lbooks = in.nextInt();
      lsignup = in.nextInt();
      lscan = in.nextInt();

      Library thisLib = new Library(lbooks, lsignup, lscan, i);
      //System.out.println("New library: " + thisLib.bpd);
      //fill library with its Books
      int id;
      for(int j = 0; j < lbooks; j++){
	//libs[i].addBooks(in, allBooks);
	id = in.nextInt();
	thisLib.books[j] = new Book(id, allBooks.get(id));
      }

      libs.add(thisLib);

      
    }//end for libraries

    //Check that end of input reached
    /*    if(in.hasNext()){
      System.out.println("________" + in.next());
    }
    else
      System.out.println("Done");
    */



    //Get fastest signup library from priority queue

    Library nextLib;
    int daysLeft = numDays, libsAdded = 0;
    String results = "";
    String bookList = "";

    int count = 0;
    for(int i = 0; i < numLibs; i++){

      nextLib = libs.poll();
      if( (daysLeft - nextLib.signupDays) >= 0){
	libsAdded++;
	daysLeft -= nextLib.signupDays;
	//System.out.println("Days left: " + daysLeft);
      }
      else
	break;

      int j, lim, used = 0;
      boolean add = true;
      if(nextLib.numBooks < nextLib.bpd*daysLeft)
	lim = nextLib.numBooks;
      else
	lim = nextLib.bpd*daysLeft;

      if(lim < 0)
	lim = nextLib.numBooks;

      //System.out.println(nextLib.idNum + " limit: " + lim + " out of " + nextLib.numBooks);
      for(j = 0; j < lim; j++){
	used++;
	//brute force search to determine if this book has already been scanned
	for(int k = 0; k < bmi; k++){
	  if(bookMemory[k] == nextLib.books[j].id){
	    add = false;
	    used--;
	  }
	}
	if(add){
	  bookList += nextLib.books[j].id;
	  bookList += " ";
	  bookMemory[++bmi] = nextLib.books[j].id;
	}
	add = true;
      }

      results += nextLib.idNum + " ";
      count++;
      results += used + "\n";
      results += bookList;
      results += "\n";
      
      bookList = "";
      used = 0;
    }

 

    //print out final result
    String s = Integer.toString(libsAdded);
    try{
      output.write(s);
      output.write("\n");
      output.write(results);
      output.close();
    } catch(Exception e){System.out.println("Error printing results");}
  }//end main



}
