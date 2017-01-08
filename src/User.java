import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable {

    private String name;
    private String email;
    private ArrayList<User> arrayListOfUserFriends = new ArrayList<>();
    private ArrayList<String> arrayListOfUserPost = new ArrayList<>();


    /**
     * Κατασκευαστής που ελέχγει αν το e-mail του χρήστη είναι αποδεκτό
     * @param name
     * @param email
     */
    public User(String name, String email, Console console) {
        this.name = name;

        if(email.startsWith("it") && email.endsWith("@uom.edu.gr"))
            this.email = email;
        else {
            console.setTextArea("User " + this.name + " has not been created. Email format is not acceptable.\n");
            this.email = "break";
        }
    }

    public User(){
    }

    /**
     * Μέθοδος που ελέγχει αν δύο χρήστες είναι φίλοι. Επιστρέφει true αν είναι και false αν δεν είναι
     * @param aUser
     * @return
     */
    public boolean isHeInMyFriendList(User aUser) {
        boolean flag = false;

        if(this.getName().equals(aUser.getName()) && this.getEmail().equals(aUser.getEmail()))
            flag = false;

        for(User u : this.arrayListOfUserFriends) {
            if(u.getName().equalsIgnoreCase(aUser.getName()) && u.getEmail().equalsIgnoreCase(aUser.getEmail()))
                flag = true;
            if(flag)
                break;
        }
        return flag;
    }

    /**
     * Επιστρέφει την λίστα με τους φίλους του χρήστη
     * @return
     */
    public ArrayList<User> getArrayListOfUserFriends() {
        return arrayListOfUserFriends;
    }

    /**
     * Ελέγχει αν ο φίλος που θέλουμε να προσθέσουμε είναι ήδη στην λίστα ή αν προσπαθούμε
     * να προσθέσουμε τον ίδιο μας ευατό και αν όχι προσθέτει τον φίλο της επιλογής μας.
     * @param aUser
     */
    public void addFriend(User aUser, Console console) {
        if(this.isHeInMyFriendList(aUser))
            console.setTextArea("You already have this friend in your list.");
        else {
            if(this.getName().equals(aUser.getName()) && this.getEmail().equals(aUser.getEmail())) {
                console.setTextArea("You can't add yourself in your friend list.");
                return;
            }
            else{
                this.arrayListOfUserFriends.add(aUser);
                aUser.arrayListOfUserFriends.add(this);
            }
            console.setTextArea(this.getName() + " and " + aUser.getName() + " are now friends!");
        }
    }

    /**
     * Δημιουργεί μια λίστα temp στην οποία αποθηκεύονται οι κοινοί φίλοι των δύο χρηστών
     * επίσης εκτυπώνει τους κοινούς φίλους των δύο χρηστών.
     * @param aUser
     * @return
     */
    public ArrayList<User> mutualFriends(User aUser, Console console) {
        ArrayList<User> temp = new ArrayList<>();

        int count = 0;
        String text = "";
        text += "Mutual friends of " + this.getName() + " and " + aUser.getName() + "\n";

        for(User u1 : this.arrayListOfUserFriends)
            for(User u2 : aUser.arrayListOfUserFriends) {
                if(u1.getName().equals(u2.getName()) && u1.getEmail().equals(u2.getEmail())) {
                    temp.add(u2);
                    count ++;
                    text += count + ": " +u2.getName() + "\n";
                    break;
                }
            }
        console.setTextArea(text);
        return temp;
    }

    /**
     * Εκτυπώνει τους φίλους του χρήστη
     */
    public void printFriends(Console console) {
        String text = "";
        text += this.getName() + " is friend with: " + "\n";
        for(User u : this.arrayListOfUserFriends)
            text += u.getName() + "\n";


        console.setTextArea(text);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getArrayListOfUserPost() {
        return arrayListOfUserPost;
    }

    public void addToArrayListOfUserPost(String message) {
        this.arrayListOfUserPost.add(message);
    }
}
