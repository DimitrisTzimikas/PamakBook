
public class ClosedGroup extends Group {

    public ClosedGroup(String name, String description) {
        super(name, description);
    }

    public void printInfo(GUIConsole console) {
        super.printInfo(console);
    }

    /**
     * ����������� ��� ��������� ��� ������ Group ��� ��� ����������� ���� ���� �� ������ �� ���� �������
     * �� ��� ������� ����� ���� ��� � ����� ������ ��� ���� ������ ����� ��� ��������� ��� ����
     * ������� ����� ���� �� �� ����� ��� ������ ������.
     */
    public void addToGroup(User aUser, GUIConsole console) {

        boolean isHeInGroupFlag = false;
        User tempUser1;

        //������� �� � ������� ��� ���� �� ����� ������� ��� ����� ������� ���
        for (int i = 0; i < this.arrayGroup.size(); i++) {
            tempUser1 = this.arrayGroup.get(i);

            if (tempUser1.getName().equals(aUser.getName())) {
                console.setTextArea(aUser.getName() + " is already enrolled in the Exam Solutions group.");
                isHeInGroupFlag = true;
            }
        }

        //��� �� ����� ����� ����� � ������� ������� ��� ����� ���� ��� �� ����
        if (this.arrayGroup.size() == 0) {
            this.arrayGroup.add(aUser);
            console.setTextArea(aUser.getName() + " has successfully enrolled in group " + this.name);
        }
        else {
            //������� ��� � ������� ��� ���� �� ������� ��� ����� ������� ���
            if (isHeInGroupFlag)
                return;

            User tempUser2;
            boolean mutualFriendFlag = false;
            int sizeOfArray = this.arrayGroup.size();

            //������� �� � ������� ��� ���� �� ����� ������� ��� ����� ���� ����� ���� �� �������
            //��� ����� ��� ������������
            for (int i = 0; i < sizeOfArray; i++) {
                tempUser2 = arrayGroup.get(i);
                if (mutualFriendFlag)
                    break;
                if (tempUser2.isHeInMyFriendList(aUser)) {
                    this.arrayGroup.add(aUser);
                    console.setTextArea(aUser.getName() + " has successfully enrolled in group " + this.name);
                    mutualFriendFlag = true;
                }
            }

            if (!mutualFriendFlag)
                console.setTextArea(aUser.getName() + " don't have a mutual friend, so she can't join the Exam Solution group.");
        }
    }
}