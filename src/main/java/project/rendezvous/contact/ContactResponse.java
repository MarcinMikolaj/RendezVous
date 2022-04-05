package project.rendezvous.contact;

public class ContactResponse {

    private boolean response;

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ContactResponse{" +
                "response=" + response +
                '}';
    }
}
