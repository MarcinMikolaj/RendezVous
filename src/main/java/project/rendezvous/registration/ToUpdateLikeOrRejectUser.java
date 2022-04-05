package project.rendezvous.registration;

public class ToUpdateLikeOrRejectUser {

    private String selectingUserEmail;
    private String selectedUserEmail;
    private String choice;

    public ToUpdateLikeOrRejectUser() {
    }

    public ToUpdateLikeOrRejectUser(String selectingUserEmail, String selectedUserEmail, String choice) {
        this.selectingUserEmail = selectingUserEmail;
        this.selectedUserEmail = selectedUserEmail;
        this.choice = choice;
    }

    public String getSelectingUserEmail() {
        return selectingUserEmail;
    }

    public void setSelectingUserEmail(String selectingUserEmail) {
        this.selectingUserEmail = selectingUserEmail;
    }

    public String getSelectedUserEmail() {
        return selectedUserEmail;
    }

    public void setSelectedUserEmail(String selectedUserEmail) {
        this.selectedUserEmail = selectedUserEmail;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "ToUpdateLikeOrRejectUser{" +
                "selectingUserEmail='" + selectingUserEmail + '\'' +
                ", selectedUserEmail='" + selectedUserEmail + '\'' +
                ", choice='" + choice + '\'' +
                '}';
    }
}
