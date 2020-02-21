import java.util.Scanner;
import java.util.HashMap;

class Library{

  public int idNum;
  public Book[] books;
  public int numBooks;
  public int signupDays;
  public int bpd;

  public Library(int numBooks, int signupDays, int bpd, int idNum){
		 
    this.numBooks = numBooks;
    this.signupDays = signupDays;
    this.bpd = bpd;
    this.idNum = idNum;

    books = new Book[numBooks];

  }

  /*public void addBooks(Scanner s, HashMap<Integer,Integer> scores){

    String line = s.nextLine();

    Scanner n = new Scanner(line);
    System.out.println(n.next());

    int id;
    for(int i = 0; i < numBooks; i++){
      id = n.nextInt();
      System.out.println(i);
      books[i] = new Book(id, scores.get(id));   //how to access book array?
      }

      }*/
  














}
