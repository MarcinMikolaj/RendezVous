package project.rendezvous.panel;

import java.io.Serializable;

public class Picture implements Serializable {

    private static final long serialVersionUID = 935263047L;

    private String bytes;

    public Picture() {
    }

    public Picture(String bytes) {
        this.bytes = bytes;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "bytes='" + bytes + '\'' +
                '}';
    }
}
