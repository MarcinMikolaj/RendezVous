package project.rendezvous.communicator;

public class Rtc {

    private String type;
    private String sdp;
    private String candidate;
    private String sdpMid;
    private String sdpMLineIndex;

    public Rtc() {
    }

    public Rtc(String type, String sdp, String candidate, String sdpMid, String sdpMLineIndex) {
        this.type = type;
        this.sdp = sdp;
        this.candidate = candidate;
        this.sdpMid = sdpMid;
        this.sdpMLineIndex = sdpMLineIndex;
    }

    public Rtc(String type, String sdp, String candidate) {
        this.type = type;
        this.sdp = sdp;
        this.candidate = candidate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getSdpMid() {
        return sdpMid;
    }

    public void setSdpMid(String sdpMid) {
        this.sdpMid = sdpMid;
    }

    public String getSdpMLineIndex() {
        return sdpMLineIndex;
    }

    public void setSdpMLineIndex(String sdpMLineIndex) {
        this.sdpMLineIndex = sdpMLineIndex;
    }

    @Override
    public String toString() {
        return "Rtc{" +
                "type='" + type + '\'' +
                ", sdp='" + sdp + '\'' +
                ", candidate='" + candidate + '\'' +
                '}';
    }
}
