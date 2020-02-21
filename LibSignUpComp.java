import java.util.*;


class LibSignUpComp implements Comparator<Library>{

    public int compare(Library l1, Library l2) {
      
      return l1.signupDays - l2.signupDays;

    }
  
}
