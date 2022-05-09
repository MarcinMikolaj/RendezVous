package project.rendezvous.communicator;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class WebrtcConnectionNegotiateMessage {

    private String username;
    private String to;
    private String content;
    private ObjectNode rtc;
    private ObjectNode candidate;
    private ObjectNode sdpMid;
    private ObjectNode sdpMLineIndex;

    public WebrtcConnectionNegotiateMessage() {}

    public WebrtcConnectionNegotiateMessage(String username, String to, String content, ObjectNode rtc, ObjectNode candidate, ObjectNode sdpMid, ObjectNode sdpMLineIndex) {
        this.username = username;
        this.to = to;
        this.content = content;
        this.rtc = rtc;
        this.candidate = candidate;
        this.sdpMid = sdpMid;
        this.sdpMLineIndex = sdpMLineIndex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ObjectNode getRtc() {
        return rtc;
    }

    public void setRtc(ObjectNode rtc) {
        this.rtc = rtc;
    }

    public ObjectNode getCandidate() {
        return candidate;
    }

    public void setCandidate(ObjectNode candidate) {
        this.candidate = candidate;
    }

    public ObjectNode getSdpMid() {
        return sdpMid;
    }

    public void setSdpMid(ObjectNode sdpMid) {
        this.sdpMid = sdpMid;
    }

    public ObjectNode getSdpMLineIndex() {
        return sdpMLineIndex;
    }

    public void setSdpMLineIndex(ObjectNode sdpMLineIndex) {
        this.sdpMLineIndex = sdpMLineIndex;
    }

    @Override
    public String toString() {
        return "WebrtcConnectionNegotiateMessage{" +
                "username='" + username + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", rtc=" + rtc +
                ", candidate=" + candidate +
                ", sdpMid=" + sdpMid +
                ", sdpMLineIndex=" + sdpMLineIndex +
                '}';
    }
}
