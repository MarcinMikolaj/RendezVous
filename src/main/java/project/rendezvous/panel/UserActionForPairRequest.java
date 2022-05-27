package project.rendezvous.panel;

public class UserActionForPairRequest {

    private String initialingUserEmail;
    private String forTheUsersEmail;
    private String request;

    public UserActionForPairRequest() {}

    public UserActionForPairRequest(String initialingUserEmail, String forTheUsersEmail, String request) {
        this.initialingUserEmail = initialingUserEmail;
        this.forTheUsersEmail = forTheUsersEmail;
        this.request = request;
    }

    public String getInitialingUserEmail() {
        return initialingUserEmail;
    }

    public void setInitialingUserEmail(String initialingUserEmail) {
        this.initialingUserEmail = initialingUserEmail;
    }

    public String getForTheUsersEmail() {
        return forTheUsersEmail;
    }

    public void setForTheUsersEmail(String forTheUsersEmail) {
        this.forTheUsersEmail = forTheUsersEmail;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "UserActionForPairRequest{" +
                "initialingUserEmail='" + initialingUserEmail + '\'' +
                ", forTheUsersEmail='" + forTheUsersEmail + '\'' +
                ", request='" + request + '\'' +
                '}';
    }
}
