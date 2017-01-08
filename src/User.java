import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable {

    private String name;
    private String email;
    private ArrayList<User> arrayListOfUserFriends = new ArrayList<>();
    private ArrayList<String> arrayListOfUserPost = new ArrayList<>();


    /**
     * ������������� ��� ������� �� �� e-mail ��� ������ ����� ��������
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
     * ������� ��� ������� �� ��� ������� ����� �����. ���������� true �� ����� ��� false �� ��� �����
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
     * ���������� ��� ����� �� ���� ������ ��� ������
     * @return
     */
    public ArrayList<User> getArrayListOfUserFriends() {
        return arrayListOfUserFriends;
    }

    /**
     * ������� �� � ����� ��� ������� �� ����������� ����� ��� ���� ����� � �� �����������
     * �� ����������� ��� ���� ��� ����� ��� �� ��� ��������� ��� ���� ��� �������� ���.
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
     * ���������� ��� ����� temp ���� ����� ������������� �� ������ ����� ��� ��� �������
     * ������ ��������� ���� ������� ������ ��� ��� �������.
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
     * ��������� ���� ������ ��� ������
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
