import java.util.ArrayList;

public class Group {

    protected String name, description;
    protected ArrayList<User> arrayGroup = new ArrayList<>();

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * ������� �� ����� ����� ��� ������ � ���
     * @param aUser
     * @return
     */
    private boolean isHeMember(User aUser) {
        boolean flag = false;

        for(User u : arrayGroup)
            if(u.equals(aUser))
                flag = true;

        return flag;
    }

    /**
     * ��������� ������ ��� ������
     * @param aUser
     * @param console
     */
    public void addToGroup(User aUser, GUIConsole console) {
        if(!this.isHeMember(aUser)) {
            arrayGroup.add(aUser);
            console.setTextArea(aUser.getName() + " has successfully enrolled in group " + this.name);
        }
        else
            console.setTextArea(aUser.getName() + " is already member of the Web Gurus group.");
    }

    /**
     * ��������� ���� ������� ��� ���������� ��� ������
     */
    public void printInfo(GUIConsole console) {
        String text = "";
        text += ("Members of group " + this.name + "\n");
        int count = 0;

        for(User u : arrayGroup) {
            count ++;
            text += count + ": " + u.getName() + "\n";
        }
        console.setTextArea(text);
    }
}
